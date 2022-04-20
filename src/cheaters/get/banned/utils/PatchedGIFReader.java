// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import com.sun.imageio.plugins.common.ReaderUtil;
import java.io.EOFException;
import java.nio.ByteOrder;
import java.io.IOException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;
import java.util.Arrays;
import java.util.Iterator;
import javax.imageio.IIOException;
import java.util.ArrayList;
import javax.imageio.spi.ImageReaderSpi;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;
import java.util.List;
import com.sun.imageio.plugins.gif.GIFImageMetadata;
import com.sun.imageio.plugins.gif.GIFStreamMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageReader;

public class PatchedGIFReader extends ImageReader
{
    ImageInputStream stream;
    boolean gotHeader;
    GIFStreamMetadata streamMetadata;
    int currIndex;
    GIFImageMetadata imageMetadata;
    List imageStartPosition;
    int imageMetadataLength;
    int numImages;
    byte[] block;
    int blockLength;
    int bitPos;
    int nextByte;
    int initCodeSize;
    int clearCode;
    int eofCode;
    int next32Bits;
    boolean lastBlockFound;
    BufferedImage theImage;
    WritableRaster theTile;
    int width;
    int height;
    int streamX;
    int streamY;
    int rowsDone;
    int interlacePass;
    static final int[] interlaceIncrement;
    static final int[] interlaceOffset;
    Rectangle sourceRegion;
    int sourceXSubsampling;
    int sourceYSubsampling;
    int sourceMinProgressivePass;
    int sourceMaxProgressivePass;
    Point destinationOffset;
    Rectangle destinationRegion;
    int updateMinY;
    int updateYStep;
    boolean decodeThisRow;
    int destY;
    byte[] rowBuf;
    
    public PatchedGIFReader(final ImageReaderSpi originatingProvider) {
        super(originatingProvider);
        this.stream = null;
        this.gotHeader = false;
        this.streamMetadata = null;
        this.currIndex = -1;
        this.imageMetadata = null;
        this.imageStartPosition = new ArrayList();
        this.numImages = -1;
        this.block = new byte[255];
        this.blockLength = 0;
        this.bitPos = 0;
        this.nextByte = 0;
        this.next32Bits = 0;
        this.lastBlockFound = false;
        this.theImage = null;
        this.theTile = null;
        this.width = -1;
        this.height = -1;
        this.streamX = -1;
        this.streamY = -1;
        this.rowsDone = 0;
        this.interlacePass = 0;
        this.decodeThisRow = true;
        this.destY = 0;
    }
    
    @Override
    public void setInput(final Object input, final boolean seekForwardOnly, final boolean ignoreMetadata) {
        super.setInput(input, seekForwardOnly, ignoreMetadata);
        if (input != null) {
            if (!(input instanceof ImageInputStream)) {
                throw new IllegalArgumentException("input not an ImageInputStream!");
            }
            this.stream = (ImageInputStream)input;
        }
        else {
            this.stream = null;
        }
        this.resetStreamSettings();
    }
    
    @Override
    public int getNumImages(final boolean allowSearch) throws IIOException {
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        if (this.seekForwardOnly && allowSearch) {
            throw new IllegalStateException("seekForwardOnly and allowSearch can't both be true!");
        }
        if (this.numImages > 0) {
            return this.numImages;
        }
        if (allowSearch) {
            this.numImages = this.locateImage(Integer.MAX_VALUE) + 1;
        }
        return this.numImages;
    }
    
    private void checkIndex(final int imageIndex) {
        if (imageIndex < this.minIndex) {
            throw new IndexOutOfBoundsException("imageIndex < minIndex!");
        }
        if (this.seekForwardOnly) {
            this.minIndex = imageIndex;
        }
    }
    
    @Override
    public int getWidth(final int imageIndex) throws IIOException {
        this.checkIndex(imageIndex);
        final int index = this.locateImage(imageIndex);
        if (index != imageIndex) {
            throw new IndexOutOfBoundsException();
        }
        this.readMetadata();
        return this.imageMetadata.imageWidth;
    }
    
    @Override
    public int getHeight(final int imageIndex) throws IIOException {
        this.checkIndex(imageIndex);
        final int index = this.locateImage(imageIndex);
        if (index != imageIndex) {
            throw new IndexOutOfBoundsException();
        }
        this.readMetadata();
        return this.imageMetadata.imageHeight;
    }
    
    @Override
    public Iterator getImageTypes(final int imageIndex) throws IIOException {
        this.checkIndex(imageIndex);
        final int index = this.locateImage(imageIndex);
        if (index != imageIndex) {
            throw new IndexOutOfBoundsException();
        }
        this.readMetadata();
        final List l = new ArrayList(1);
        byte[] colorTable;
        if (this.imageMetadata.localColorTable != null) {
            colorTable = this.imageMetadata.localColorTable;
        }
        else {
            colorTable = this.streamMetadata.globalColorTable;
        }
        final int length = colorTable.length / 3;
        int bits;
        if (length == 2) {
            bits = 1;
        }
        else if (length == 4) {
            bits = 2;
        }
        else if (length == 8 || length == 16) {
            bits = 4;
        }
        else {
            bits = 8;
        }
        final int lutLength = 1 << bits;
        final byte[] r = new byte[lutLength];
        final byte[] g = new byte[lutLength];
        final byte[] b = new byte[lutLength];
        int rgbIndex = 0;
        for (int i = 0; i < length; ++i) {
            r[i] = colorTable[rgbIndex++];
            g[i] = colorTable[rgbIndex++];
            b[i] = colorTable[rgbIndex++];
        }
        byte[] a = null;
        if (this.imageMetadata.transparentColorFlag) {
            a = new byte[lutLength];
            Arrays.fill(a, (byte)(-1));
            final int idx = Math.min(this.imageMetadata.transparentColorIndex, lutLength - 1);
            a[idx] = 0;
        }
        final int[] bitsPerSample = { bits };
        l.add(ImageTypeSpecifier.createIndexed(r, g, b, a, bits, 0));
        return l.iterator();
    }
    
    @Override
    public ImageReadParam getDefaultReadParam() {
        return new ImageReadParam();
    }
    
    @Override
    public IIOMetadata getStreamMetadata() throws IIOException {
        this.readHeader();
        return this.streamMetadata;
    }
    
    @Override
    public IIOMetadata getImageMetadata(final int imageIndex) throws IIOException {
        this.checkIndex(imageIndex);
        final int index = this.locateImage(imageIndex);
        if (index != imageIndex) {
            throw new IndexOutOfBoundsException("Bad image index!");
        }
        this.readMetadata();
        return this.imageMetadata;
    }
    
    private void initNext32Bits() {
        this.next32Bits = (this.block[0] & 0xFF);
        this.next32Bits |= (this.block[1] & 0xFF) << 8;
        this.next32Bits |= (this.block[2] & 0xFF) << 16;
        this.next32Bits |= this.block[3] << 24;
        this.nextByte = 4;
    }
    
    private int getCode(final int codeSize, final int codeMask) throws IOException {
        if (this.bitPos + codeSize > 32) {
            return this.eofCode;
        }
        final int code = this.next32Bits >> this.bitPos & codeMask;
        this.bitPos += codeSize;
        while (this.bitPos >= 8 && !this.lastBlockFound) {
            this.next32Bits >>>= 8;
            this.bitPos -= 8;
            if (this.nextByte >= this.blockLength) {
                this.blockLength = this.stream.readUnsignedByte();
                if (this.blockLength == 0) {
                    this.lastBlockFound = true;
                    return code;
                }
                int left = this.blockLength;
                int off = 0;
                while (left > 0) {
                    final int nbytes = this.stream.read(this.block, off, left);
                    off += nbytes;
                    left -= nbytes;
                }
                this.nextByte = 0;
            }
            this.next32Bits |= this.block[this.nextByte++] << 24;
        }
        return code;
    }
    
    public void initializeStringTable(final int[] prefix, final byte[] suffix, final byte[] initial, final int[] length) {
        final int numEntries = 1 << this.initCodeSize;
        for (int i = 0; i < numEntries; ++i) {
            prefix[i] = -1;
            suffix[i] = (byte)i;
            initial[i] = (byte)i;
            length[i] = 1;
        }
        for (int i = numEntries; i < 4096; ++i) {
            prefix[i] = -1;
            length[i] = 1;
        }
    }
    
    private void outputRow() {
        final int width = Math.min(this.sourceRegion.width, this.destinationRegion.width * this.sourceXSubsampling);
        int destX = this.destinationRegion.x;
        if (this.sourceXSubsampling == 1) {
            this.theTile.setDataElements(destX, this.destY, width, 1, this.rowBuf);
        }
        else {
            for (int x = 0; x < width; x += this.sourceXSubsampling, ++destX) {
                this.theTile.setSample(destX, this.destY, 0, this.rowBuf[x] & 0xFF);
            }
        }
        if (this.updateListeners != null) {
            final int[] bands = { 0 };
            this.processImageUpdate(this.theImage, destX, this.destY, width, 1, 1, this.updateYStep, bands);
        }
    }
    
    private void computeDecodeThisRow() {
        this.decodeThisRow = (this.destY < this.destinationRegion.y + this.destinationRegion.height && this.streamY >= this.sourceRegion.y && this.streamY < this.sourceRegion.y + this.sourceRegion.height && (this.streamY - this.sourceRegion.y) % this.sourceYSubsampling == 0);
    }
    
    private void outputPixels(final byte[] string, final int len) {
        if (this.interlacePass < this.sourceMinProgressivePass || this.interlacePass > this.sourceMaxProgressivePass) {
            return;
        }
        for (int i = 0; i < len; ++i) {
            if (this.streamX >= this.sourceRegion.x) {
                this.rowBuf[this.streamX - this.sourceRegion.x] = string[i];
            }
            ++this.streamX;
            if (this.streamX == this.width) {
                ++this.rowsDone;
                this.processImageProgress(100.0f * this.rowsDone / this.height);
                if (this.decodeThisRow) {
                    this.outputRow();
                }
                this.streamX = 0;
                if (this.imageMetadata.interlaceFlag) {
                    this.streamY += PatchedGIFReader.interlaceIncrement[this.interlacePass];
                    if (this.streamY >= this.height) {
                        if (this.updateListeners != null) {
                            this.processPassComplete(this.theImage);
                        }
                        ++this.interlacePass;
                        if (this.interlacePass > this.sourceMaxProgressivePass) {
                            return;
                        }
                        this.streamY = PatchedGIFReader.interlaceOffset[this.interlacePass];
                        this.startPass(this.interlacePass);
                    }
                }
                else {
                    ++this.streamY;
                }
                this.destY = this.destinationRegion.y + (this.streamY - this.sourceRegion.y) / this.sourceYSubsampling;
                this.computeDecodeThisRow();
            }
        }
    }
    
    private void readHeader() throws IIOException {
        if (this.gotHeader) {
            return;
        }
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        this.streamMetadata = new GIFStreamMetadata();
        try {
            this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
            final byte[] signature = new byte[6];
            this.stream.readFully(signature);
            final StringBuffer version = new StringBuffer(3);
            version.append((char)signature[3]);
            version.append((char)signature[4]);
            version.append((char)signature[5]);
            this.streamMetadata.version = version.toString();
            this.streamMetadata.logicalScreenWidth = this.stream.readUnsignedShort();
            this.streamMetadata.logicalScreenHeight = this.stream.readUnsignedShort();
            final int packedFields = this.stream.readUnsignedByte();
            final boolean globalColorTableFlag = (packedFields & 0x80) != 0x0;
            this.streamMetadata.colorResolution = (packedFields >> 4 & 0x7) + 1;
            this.streamMetadata.sortFlag = ((packedFields & 0x8) != 0x0);
            final int numGCTEntries = 1 << (packedFields & 0x7) + 1;
            this.streamMetadata.backgroundColorIndex = this.stream.readUnsignedByte();
            this.streamMetadata.pixelAspectRatio = this.stream.readUnsignedByte();
            if (globalColorTableFlag) {
                this.streamMetadata.globalColorTable = new byte[3 * numGCTEntries];
                this.stream.readFully(this.streamMetadata.globalColorTable);
            }
            else {
                this.streamMetadata.globalColorTable = null;
            }
            this.imageStartPosition.add(this.stream.getStreamPosition());
        }
        catch (IOException e) {
            throw new IIOException("I/O error reading header!", e);
        }
        this.gotHeader = true;
    }
    
    private boolean skipImage() throws IIOException {
        try {
            while (true) {
                final int blockType = this.stream.readUnsignedByte();
                if (blockType == 44) {
                    this.stream.skipBytes(8);
                    final int packedFields = this.stream.readUnsignedByte();
                    if ((packedFields & 0x80) != 0x0) {
                        final int bits = (packedFields & 0x7) + 1;
                        this.stream.skipBytes(3 * (1 << bits));
                    }
                    this.stream.skipBytes(1);
                    int length = 0;
                    do {
                        length = this.stream.readUnsignedByte();
                        this.stream.skipBytes(length);
                    } while (length > 0);
                    return true;
                }
                if (blockType == 59) {
                    return false;
                }
                if (blockType == 33) {
                    final int label = this.stream.readUnsignedByte();
                    int length = 0;
                    do {
                        length = this.stream.readUnsignedByte();
                        this.stream.skipBytes(length);
                    } while (length > 0);
                }
                else {
                    if (blockType == 0) {
                        return false;
                    }
                    int length2 = 0;
                    do {
                        length2 = this.stream.readUnsignedByte();
                        this.stream.skipBytes(length2);
                    } while (length2 > 0);
                }
            }
        }
        catch (EOFException e2) {
            return false;
        }
        catch (IOException e) {
            throw new IIOException("I/O error locating image!", e);
        }
    }
    
    private int locateImage(final int imageIndex) throws IIOException {
        this.readHeader();
        try {
            int index = Math.min(imageIndex, this.imageStartPosition.size() - 1);
            final Long l = this.imageStartPosition.get(index);
            this.stream.seek(l);
            while (index < imageIndex) {
                if (!this.skipImage()) {
                    return --index;
                }
                final Long l2 = new Long(this.stream.getStreamPosition());
                this.imageStartPosition.add(l2);
                ++index;
            }
        }
        catch (IOException e) {
            throw new IIOException("Couldn't seek!", e);
        }
        if (this.currIndex != imageIndex) {
            this.imageMetadata = null;
        }
        return this.currIndex = imageIndex;
    }
    
    private byte[] concatenateBlocks() throws IOException {
        byte[] data = new byte[0];
        while (true) {
            final int length = this.stream.readUnsignedByte();
            if (length == 0) {
                break;
            }
            final byte[] newData = new byte[data.length + length];
            System.arraycopy(data, 0, newData, 0, data.length);
            this.stream.readFully(newData, data.length, length);
            data = newData;
        }
        return data;
    }
    
    private void readMetadata() throws IIOException {
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        try {
            this.imageMetadata = new GIFImageMetadata();
            final long startPosition = this.stream.getStreamPosition();
            while (true) {
                final int blockType = this.stream.readUnsignedByte();
                if (blockType == 44) {
                    this.imageMetadata.imageLeftPosition = this.stream.readUnsignedShort();
                    this.imageMetadata.imageTopPosition = this.stream.readUnsignedShort();
                    this.imageMetadata.imageWidth = this.stream.readUnsignedShort();
                    this.imageMetadata.imageHeight = this.stream.readUnsignedShort();
                    final int idPackedFields = this.stream.readUnsignedByte();
                    final boolean localColorTableFlag = (idPackedFields & 0x80) != 0x0;
                    this.imageMetadata.interlaceFlag = ((idPackedFields & 0x40) != 0x0);
                    this.imageMetadata.sortFlag = ((idPackedFields & 0x20) != 0x0);
                    final int numLCTEntries = 1 << (idPackedFields & 0x7) + 1;
                    if (localColorTableFlag) {
                        this.imageMetadata.localColorTable = new byte[3 * numLCTEntries];
                        this.stream.readFully(this.imageMetadata.localColorTable);
                    }
                    else {
                        this.imageMetadata.localColorTable = null;
                    }
                    this.imageMetadataLength = (int)(this.stream.getStreamPosition() - startPosition);
                    return;
                }
                if (blockType == 33) {
                    final int label = this.stream.readUnsignedByte();
                    if (label == 249) {
                        final int gceLength = this.stream.readUnsignedByte();
                        final int gcePackedFields = this.stream.readUnsignedByte();
                        this.imageMetadata.disposalMethod = (gcePackedFields >> 2 & 0x3);
                        this.imageMetadata.userInputFlag = ((gcePackedFields & 0x2) != 0x0);
                        this.imageMetadata.transparentColorFlag = ((gcePackedFields & 0x1) != 0x0);
                        this.imageMetadata.delayTime = this.stream.readUnsignedShort();
                        this.imageMetadata.transparentColorIndex = this.stream.readUnsignedByte();
                        this.stream.readUnsignedByte();
                    }
                    else if (label == 1) {
                        final int length = this.stream.readUnsignedByte();
                        this.imageMetadata.hasPlainTextExtension = true;
                        this.imageMetadata.textGridLeft = this.stream.readUnsignedShort();
                        this.imageMetadata.textGridTop = this.stream.readUnsignedShort();
                        this.imageMetadata.textGridWidth = this.stream.readUnsignedShort();
                        this.imageMetadata.textGridHeight = this.stream.readUnsignedShort();
                        this.imageMetadata.characterCellWidth = this.stream.readUnsignedByte();
                        this.imageMetadata.characterCellHeight = this.stream.readUnsignedByte();
                        this.imageMetadata.textForegroundColor = this.stream.readUnsignedByte();
                        this.imageMetadata.textBackgroundColor = this.stream.readUnsignedByte();
                        this.imageMetadata.text = this.concatenateBlocks();
                    }
                    else if (label == 254) {
                        final byte[] comment = this.concatenateBlocks();
                        if (this.imageMetadata.comments == null) {
                            this.imageMetadata.comments = new ArrayList<byte[]>();
                        }
                        this.imageMetadata.comments.add(comment);
                    }
                    else if (label == 255) {
                        final int blockSize = this.stream.readUnsignedByte();
                        final byte[] applicationID = new byte[8];
                        final byte[] authCode = new byte[3];
                        final byte[] blockData = new byte[blockSize];
                        this.stream.readFully(blockData);
                        int offset = this.copyData(blockData, 0, applicationID);
                        offset = this.copyData(blockData, offset, authCode);
                        byte[] applicationData = this.concatenateBlocks();
                        if (offset < blockSize) {
                            final int len = blockSize - offset;
                            final byte[] data = new byte[len + applicationData.length];
                            System.arraycopy(blockData, offset, data, 0, len);
                            System.arraycopy(applicationData, 0, data, len, applicationData.length);
                            applicationData = data;
                        }
                        if (this.imageMetadata.applicationIDs == null) {
                            this.imageMetadata.applicationIDs = new ArrayList<byte[]>();
                            this.imageMetadata.authenticationCodes = new ArrayList<byte[]>();
                            this.imageMetadata.applicationData = new ArrayList<byte[]>();
                        }
                        this.imageMetadata.applicationIDs.add(applicationID);
                        this.imageMetadata.authenticationCodes.add(authCode);
                        this.imageMetadata.applicationData.add(applicationData);
                    }
                    else {
                        int length = 0;
                        do {
                            length = this.stream.readUnsignedByte();
                            this.stream.skipBytes(length);
                        } while (length > 0);
                    }
                }
                else {
                    if (blockType == 59) {
                        throw new IndexOutOfBoundsException("Attempt to read past end of image sequence!");
                    }
                    throw new IIOException("Unexpected block type " + blockType + "!");
                }
            }
        }
        catch (IIOException iioe) {
            throw iioe;
        }
        catch (IOException ioe) {
            throw new IIOException("I/O error reading image metadata!", ioe);
        }
    }
    
    private int copyData(final byte[] src, final int offset, final byte[] dst) {
        int len = dst.length;
        final int rest = src.length - offset;
        if (len > rest) {
            len = rest;
        }
        System.arraycopy(src, offset, dst, 0, len);
        return offset + len;
    }
    
    private void startPass(final int pass) {
        if (this.updateListeners == null) {
            return;
        }
        int y = 0;
        int yStep = 1;
        if (this.imageMetadata.interlaceFlag) {
            y = PatchedGIFReader.interlaceOffset[this.interlacePass];
            yStep = PatchedGIFReader.interlaceIncrement[this.interlacePass];
        }
        final int[] vals = ReaderUtil.computeUpdatedPixels(this.sourceRegion, this.destinationOffset, this.destinationRegion.x, this.destinationRegion.y, this.destinationRegion.x + this.destinationRegion.width - 1, this.destinationRegion.y + this.destinationRegion.height - 1, this.sourceXSubsampling, this.sourceYSubsampling, 0, y, this.destinationRegion.width, (this.destinationRegion.height + yStep - 1) / yStep, 1, yStep);
        this.updateMinY = vals[1];
        this.updateYStep = vals[5];
        final int[] bands = { 0 };
        this.processPassStarted(this.theImage, this.interlacePass, this.sourceMinProgressivePass, this.sourceMaxProgressivePass, 0, this.updateMinY, 1, this.updateYStep, bands);
    }
    
    @Override
    public BufferedImage read(final int imageIndex, ImageReadParam param) throws IIOException {
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        this.checkIndex(imageIndex);
        final int index = this.locateImage(imageIndex);
        if (index != imageIndex) {
            throw new IndexOutOfBoundsException("imageIndex out of bounds!");
        }
        this.clearAbortRequest();
        this.readMetadata();
        if (param == null) {
            param = this.getDefaultReadParam();
        }
        final Iterator imageTypes = this.getImageTypes(imageIndex);
        this.theImage = ImageReader.getDestination(param, imageTypes, this.imageMetadata.imageWidth, this.imageMetadata.imageHeight);
        this.theTile = this.theImage.getWritableTile(0, 0);
        this.width = this.imageMetadata.imageWidth;
        this.height = this.imageMetadata.imageHeight;
        this.streamX = 0;
        this.streamY = 0;
        this.rowsDone = 0;
        this.interlacePass = 0;
        this.sourceRegion = new Rectangle(0, 0, 0, 0);
        this.destinationRegion = new Rectangle(0, 0, 0, 0);
        ImageReader.computeRegions(param, this.width, this.height, this.theImage, this.sourceRegion, this.destinationRegion);
        this.destinationOffset = new Point(this.destinationRegion.x, this.destinationRegion.y);
        this.sourceXSubsampling = param.getSourceXSubsampling();
        this.sourceYSubsampling = param.getSourceYSubsampling();
        this.sourceMinProgressivePass = Math.max(param.getSourceMinProgressivePass(), 0);
        this.sourceMaxProgressivePass = Math.min(param.getSourceMaxProgressivePass(), 3);
        this.destY = this.destinationRegion.y + (this.streamY - this.sourceRegion.y) / this.sourceYSubsampling;
        this.computeDecodeThisRow();
        this.processImageStarted(imageIndex);
        this.startPass(0);
        this.rowBuf = new byte[this.width];
        try {
            this.initCodeSize = this.stream.readUnsignedByte();
            this.blockLength = this.stream.readUnsignedByte();
            int nbytes;
            for (int left = this.blockLength, off = 0; left > 0; left -= nbytes, off += nbytes) {
                nbytes = this.stream.read(this.block, off, left);
            }
            this.bitPos = 0;
            this.nextByte = 0;
            this.lastBlockFound = false;
            this.interlacePass = 0;
            this.initNext32Bits();
            this.clearCode = 1 << this.initCodeSize;
            this.eofCode = this.clearCode + 1;
            int oldCode = 0;
            final int[] prefix = new int[4096];
            final byte[] suffix = new byte[4096];
            final byte[] initial = new byte[4096];
            final int[] length = new int[4096];
            final byte[] string = new byte[4096];
            this.initializeStringTable(prefix, suffix, initial, length);
            int tableIndex = (1 << this.initCodeSize) + 2;
            int codeSize = this.initCodeSize + 1;
            int codeMask = (1 << codeSize) - 1;
            while (!this.abortRequested()) {
                int code = this.getCode(codeSize, codeMask);
                if (code == this.clearCode) {
                    this.initializeStringTable(prefix, suffix, initial, length);
                    tableIndex = (1 << this.initCodeSize) + 2;
                    codeSize = this.initCodeSize + 1;
                    codeMask = (1 << codeSize) - 1;
                    code = this.getCode(codeSize, codeMask);
                    if (code == this.eofCode) {
                        this.processImageComplete();
                        return this.theImage;
                    }
                }
                else {
                    if (code == this.eofCode) {
                        this.processImageComplete();
                        return this.theImage;
                    }
                    int newSuffixIndex;
                    if (code < tableIndex) {
                        newSuffixIndex = code;
                    }
                    else {
                        newSuffixIndex = oldCode;
                        if (code != tableIndex) {
                            this.processWarningOccurred("Out-of-sequence code!");
                        }
                    }
                    try {
                        final int ti = tableIndex;
                        final int oc = oldCode;
                        prefix[ti] = oc;
                        suffix[ti] = initial[newSuffixIndex];
                        initial[ti] = initial[oc];
                        length[ti] = length[oc] + 1;
                        if (++tableIndex == 1 << codeSize && tableIndex < 4096) {
                            ++codeSize;
                            codeMask = (1 << codeSize) - 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e2) {
                        this.initializeStringTable(prefix, suffix, initial, length);
                        tableIndex = (1 << this.initCodeSize) + 2;
                        codeSize = this.initCodeSize + 1;
                        codeMask = (1 << codeSize) - 1;
                        code = this.getCode(codeSize, codeMask);
                        if (code == this.eofCode) {
                            this.processImageComplete();
                            return this.theImage;
                        }
                    }
                }
                int c = code;
                final int len = length[c];
                for (int i = len - 1; i >= 0; --i) {
                    string[i] = suffix[c];
                    c = prefix[c];
                }
                this.outputPixels(string, len);
                oldCode = code;
            }
            this.processReadAborted();
            return this.theImage;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IIOException("I/O error reading image!", e);
        }
    }
    
    @Override
    public void reset() {
        super.reset();
        this.resetStreamSettings();
    }
    
    private void resetStreamSettings() {
        this.gotHeader = false;
        this.streamMetadata = null;
        this.currIndex = -1;
        this.imageMetadata = null;
        this.imageStartPosition = new ArrayList();
        this.numImages = -1;
        this.blockLength = 0;
        this.bitPos = 0;
        this.nextByte = 0;
        this.next32Bits = 0;
        this.lastBlockFound = false;
        this.theImage = null;
        this.theTile = null;
        this.width = -1;
        this.height = -1;
        this.streamX = -1;
        this.streamY = -1;
        this.rowsDone = 0;
        this.interlacePass = 0;
    }
    
    static {
        interlaceIncrement = new int[] { 8, 8, 4, 2, -1 };
        interlaceOffset = new int[] { 0, 4, 2, 1, -1 };
    }
}

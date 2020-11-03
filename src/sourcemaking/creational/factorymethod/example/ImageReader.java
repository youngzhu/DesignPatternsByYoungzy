package sourcemaking.creational.factorymethod.example;

interface ImageReader {
    DecodeImage getDecodeImage();
}

class GifReader implements ImageReader {
    private DecodeImage decodeImage;

    public GifReader(String image) {
        this.decodeImage = new DecodeImage(image);
    }

    @Override
    public DecodeImage getDecodeImage() {
        return this.decodeImage;
    }
}

class JpegReader implements ImageReader {
    private DecodeImage decodeImage;

    public JpegReader(String image) {
        this.decodeImage = new DecodeImage(image);
    }

    @Override
    public DecodeImage getDecodeImage() {
        return this.decodeImage;
    }
}
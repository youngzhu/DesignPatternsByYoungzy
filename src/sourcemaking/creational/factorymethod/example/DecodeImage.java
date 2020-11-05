package sourcemaking.creational.factorymethod.example;

class DecodeImage {
    private String image;

    public DecodeImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return image + ": is decoded";
    }
}

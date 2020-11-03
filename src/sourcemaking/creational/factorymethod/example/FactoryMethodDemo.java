package sourcemaking.creational.factorymethod.example;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        DecodeImage decodeImage;
        ImageReader reader = null;

//        String image = args[0];
        // 为了方便测试
        String image = "xx.gif";
        image = "demo.jpeg";

        String format = image.substring(image.indexOf('.') + 1, image.length());
        if (format.equals("gif")) {
            reader = new GifReader(image);
        }

        if (format.equals("jpeg")) {
            reader = new JpegReader(image);
        }

        assert reader != null;

        decodeImage = reader.getDecodeImage();
        System.out.println(decodeImage);
    }
}

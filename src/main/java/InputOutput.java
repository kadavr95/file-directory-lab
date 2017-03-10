import java.io.*;

/**
 * Created by admin on 10.03.2017.
 */
public class InputOutput {
    public static void main(String[] args){
        byte[] buf = new byte[1024];
        try (InputStream is = new BufferedInputStream("")) {
            while (true) {
                int n = is.read(buf);
                if (n < 0)
                    break;
                for (int i=0, i<n, i++)
                    System.out.println(n);
            }
        }
//        try (InputStream is = new FileInputStream("")) {
//            while (true) {
//                int c = is.read();
//                if (c < 0)
//                    break;
//                System.out.println(c);
//            }
//        }
//
//
//        byte[] buf = new byte[1024];
//        int n =is.read(buf);
//        try (InputStream is = new FileInputStream("")) {
//            while (true) {
//                int n = is.read(buf);
//                if (n < 0)
//                    break;
//                for (int i=0, i<n, i++)
//                    System.out.println(n);
//            }
//        }
//
//        try (InputStream is = new BufferedInputStream(new FileInputStream(""))){
//            while(true){
//                int c = is.read();
//                if (c < 0)
//                    break;
//            }
//        }
//
//        OutputStream os = new BufferedOutputStream((new FileOutputStream("")))
//        os.write(c);
    }
}

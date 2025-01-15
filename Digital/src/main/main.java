package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

public class main {
	public class FS80HExample {
	    private static FutronicSdk m_FutronicSdk = null;
	    
	    public static void main(String[] args) {
	        m_FutronicSdk = new FutronicSdk();
	        
	        if (m_FutronicSdk.OpenDevice() != FutronicSdk.FTR_OK) {
	            System.out.println("Erro ao conectar com o dispositivo.");
	            return;
	        }
	        
	        BufferedImage imagemDigital = captureFingerprint();
	        if (imagemDigital != null) {
	            saveFingerprintImage(imagemDigital);
	        } else {
	            System.out.println("Falha ao capturar a digital.");
	        }
	        m_FutronicSdk.CloseDevice();
	    }
	    public static BufferedImage captureFingerprint() {
	        byte[] imageData = new byte[2048];
	        
	        int result = m_FutronicSdk.AcquireFingerprint(imageData);
	        if (result != FutronicSdk.FTR_OK) {
	            System.out.println("Erro ao capturar a digital: " + result);
	            return null;
	        }
	        
	        BufferedImage image = convertToBufferedImage(imageData);
	        return image;
	    }
	    
	    public static void saveFingerprintImage(BufferedImage image) {
	        try {
	            String caminho = "C:/images_java_scan" + System.currentTimeMillis() + ".png";
	            File file = new File(caminho);
	            ImageIO.write(image, "PNG", file);
	            System.out.println("Imagem salva em: " + caminho);
	        } catch (Exception e) {
	            System.out.println("Erro ao salvar a imagem: " + e.getMessage());
	        }
	    }
	    
	    private static BufferedImage convertToBufferedImage(byte[] imageData) {
	        return new BufferedImage(256, 300, BufferedImage.TYPE_BYTE_GRAY);
	    }
	}
}
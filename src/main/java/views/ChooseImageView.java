package views;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseImageView extends JFileChooser{
    
    public ChooseImageView(){
        //initFileChooser();
        setDialogTitle("Chọn ảnh");
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setDragEnabled(true);
        setCurrentDirectory(new File("C:\\"));
        setDialogType(JFileChooser.OPEN_DIALOG);
        FileFilter imageFilter = new FileNameExtensionFilter("Hình ảnh(jpg, png, gif,...)", 
                ImageIO.getReaderFileSuffixes());
        setFileFilter(imageFilter);
    }
    
    public void initFileChooser(){
        
    }
}

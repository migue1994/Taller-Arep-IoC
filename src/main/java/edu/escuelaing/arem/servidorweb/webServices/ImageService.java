package edu.escuelaing.arem.servidorweb.webServices;

import edu.escuelaing.arem.servidorweb.annotations.Web;

public class ImageService {
    @Web("/image.html")
    public static String getImage(){
        return "<html>\n"
                + "<body>\n"
                + "<img src=\"paisaje1.jpg\">"
                + "</body>"
                + "</html>";
    }
}

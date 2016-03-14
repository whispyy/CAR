package tp2;

import tp2.ftpClient.FTPCommandes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by jf on 14/03/16.
 */
public class AdditionnalResources extends FTPResource{

    public static String searchFile(String name) throws IOException {
        System.out.println(name);
        File f;

        f=new File(commandes.getCurrentDir()+"/"+name);
        if(f.exists()){
            System.out.println(f);
            if(f.isFile()){
                System.out.println("Dans méthode isFile");
                return read(f);
            }

            if(f.isDirectory()){
                if(commandes.CMDCWD(name)){
                    return list(f);
                }


            }
        }else{
            System.out.println("FileNotFound");
            boolean retour = commandes.CMDRETR(name);
            System.out.println(f);

            if(retour){

                if(!login)
                    return FTPResource.logIn();
                System.out.println("Dans méthode isFile");
                return read(f);
            }
            if(!retour){
                f.delete();
                Files.createDirectory(f.toPath());
                System.out.println("dossier :" +f.isDirectory());
                if(commandes.CMDCWD(name)){
                    return list(f);
                }
            }




        }
        return "<h1>FILE NOT FOUND</h1>";
    }

    public static String read(File f) throws IOException{
        String result ="";
        FileInputStream br = new FileInputStream(f);

        byte [] buffer = new byte[(int) f.length()];
        while(br.read(buffer) > 0){
            result+= new String(buffer);
        }
        br.close();

        return result;
    }

    public static String write(File f, String content) throws IOException{
        String result ="";
        FileOutputStream br = new FileOutputStream(f);

        br.write(content.getBytes(),0,content.getBytes().length);
        br.close();
        return result;
    }

    public static String list(File f) throws IOException{
        String result="";
        System.out.println(commandes.getCurrentDir());
        result = commandes.CMDLIST("");
        String[] test = result.split("\r\n");
        result="";
        for(int i = 0; i < test.length - 1; i++){
            if(test[i].length()>0){

                result+="<a href='/rest/tp2/ftp/"+commandes.getCurrentDir()+"/"+test[i]+"'>" +  test[i] + "</a></br>";
            }
        }
        result+="<a href='/rest/tp2/ftp/"+ commandes.getCurrentDir()+"/..'>..</a></br>";
        System.out.println("here");
        return result ;

    }
}

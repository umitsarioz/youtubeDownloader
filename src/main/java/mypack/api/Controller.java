package mypack.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;

import org.springframework.web.bind.annotation.*;

import mypack.Business.IYtDownloaderService;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController //uygulamanın rest oldugunu belirtmek için annation ile belirtiyoruz.
// @RequestMapping("/api") //butun operasyonlar api ile başlar.Bunun bir api oldugunu belirten anatasyondur.Kaldırabiliriz ama durabilirde durursa linki ona göre ayarlamak lazım.
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class Controller {

    private IYtDownloaderService ytDownloaderService;
    private Object Resource;

    @Autowired // Service'i sadece manager implemente ediyor.Otomatik bulur.
    public Controller(IYtDownloaderService ytDownloaderService) {
        this.ytDownloaderService = ytDownloaderService;
    }

    public Controller() {

    }

    //Eğer yukarıdan apiyi silersek sadece / ile localhosta ulasabiliriz.s
    @GetMapping("/") //yotube linki buraya yazılacak.sanırım :)
    public ModelAndView get() { //youtube linkini get ile alacak metod
        return ytDownloaderService.getLink(); //burası degiscek.
    }

    //    @PostMapping("/test") //yotube linki buraya yazılacak.sanırım :)
//    public String url(@RequestBody Map<String, String> myuri) { //youtube linkini get ile alacak metod
//        return myuri.get("url");
//    }
    @CrossOrigin
    @RequestMapping(value = "/dw", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getTest(@RequestBody Map<String, String> json) {
        try {
            getVideo(json.get("url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Video downloaded to 'Downloads' folder";
    }

    public ModelAndView method() {
        return new ModelAndView("redirect:" + "http://localhost:5000");
    }

    public void getVideo(String url) throws Exception {
        Runtime rt = Runtime.getRuntime();
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "python3", "/home/ytdownloader/ytd/src/main/java/mypack/dw.py", url);
            System.out.println(builder.command());
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

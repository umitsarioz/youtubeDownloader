package mypack.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mypack.DataAccess.IYtDownloaderDal;
import org.springframework.web.servlet.ModelAndView;

@Service //bunun bir iş katmanı oldugunu belirten anatasyon
public class YtDownloaderManager implements IYtDownloaderService {
    //iş katmanı

    private IYtDownloaderDal ytDownloaderDal; //Data access layer ile bağlıyoruz.

    @Autowired //bu database olmadıgı için belki gereksiz olabilir.
    public YtDownloaderManager(IYtDownloaderDal ytDownloaderDal) { //constructor olusturuldu.
        this.ytDownloaderDal = ytDownloaderDal;
    }
    @Override
    public ModelAndView getLink() { //linki alacağımız metod.
        return ytDownloaderDal.getLink();
    }

}
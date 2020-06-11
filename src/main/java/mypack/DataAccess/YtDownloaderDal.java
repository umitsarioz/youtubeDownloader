package mypack.DataAccess;

import org.springframework.stereotype.Repository;
import mypack.api.Controller;
import org.springframework.web.servlet.ModelAndView;

@Repository //burası repository oldugunu belirten anatasyon
public class YtDownloaderDal implements IYtDownloaderDal  {
    Controller ctl;
    @Override
    public ModelAndView getLink() { //linki alacağımız metod.
        ctl = new Controller();
        return ctl.method();
    }

}

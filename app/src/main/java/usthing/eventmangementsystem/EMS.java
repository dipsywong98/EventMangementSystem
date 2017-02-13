package usthing.eventmangementsystem;

import io.skygear.skygear.SkygearApplication;

public class EMS extends SkygearApplication {
    @Override
    public String getSkygearEndpoint() {
        return "https://testestt.skygeario.com/";
    }

    @Override
    public String getApiKey() {
        //return "f54a2884a45d48889e2a0e9165e7c623";//usthing
        return "274fac5dffd348d8b6f20afd2844d110";//testestt
    }
}
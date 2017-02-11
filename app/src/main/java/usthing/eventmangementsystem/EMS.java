package usthing.eventmangementsystem;

import io.skygear.skygear.SkygearApplication;

public class EMS extends SkygearApplication {
    @Override
    public String getSkygearEndpoint() {
        return "https://testestt.skygeario.com/";
    }

    @Override
    public String getApiKey() {
        return "274fac5dffd348d8b6f20afd2844d110";
    }
}
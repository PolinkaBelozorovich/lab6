package extensions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpMethod;

import java.lang.reflect.Method;

public class FireFoxDriverEx extends FirefoxDriver {
    public FireFoxDriverEx() throws Exception {
        this(new FirefoxOptions());
    }

    public FireFoxDriverEx(FirefoxOptions options) throws Exception {
        this(GeckoDriverService.createDefaultService(), options);
    }

    public FireFoxDriverEx(GeckoDriverService service, FirefoxOptions options) throws Exception {
        super(service, options);
        CommandInfo cmd = new CommandInfo("/session/:sessionId/chromium/send_command_and_get_result", HttpMethod.POST);
        Method defineCommand = HttpCommandExecutor.class.getDeclaredMethod("defineCommand", String.class, CommandInfo.class);
        defineCommand.setAccessible(true);
        defineCommand.invoke(super.getCommandExecutor(), "sendCommand", cmd);
    }

}

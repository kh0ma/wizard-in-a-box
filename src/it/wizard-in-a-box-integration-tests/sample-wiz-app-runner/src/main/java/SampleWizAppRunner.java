import com.github.kh0ma.tools.dropwizard.box.it.app.HelloWorldApplication;
import com.github.kh0ma.dropwizard.box.it.support.WizRunner;

public class SampleWizAppRunner {
    public static void main(String[] args) throws Exception {
        new WizRunner(new HelloWorldApplication(), "sample-wiz-app-runner", "hello-world-as-dw-jar.yml").run();
    }

}
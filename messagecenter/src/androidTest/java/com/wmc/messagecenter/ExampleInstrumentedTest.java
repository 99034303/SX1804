<<<<<<< HEAD:messagecenter/src/androidTest/java/com/wmc/messagecenter/ExampleInstrumentedTest.java
package com.wmc.messagecenter;
=======
package com.bw.phonecontact;
>>>>>>> 57dd8bd7ad68fd3093dca08cbd2246d7e5373682:phonecontact/src/androidTest/java/com/bw/phonecontact/ExampleInstrumentedTest.java

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
<<<<<<< HEAD:messagecenter/src/androidTest/java/com/wmc/messagecenter/ExampleInstrumentedTest.java
        assertEquals("com.wmc.messagecenter.test", appContext.getPackageName());
=======
        assertEquals("com.bw.phonecontact", appContext.getPackageName());
>>>>>>> 57dd8bd7ad68fd3093dca08cbd2246d7e5373682:phonecontact/src/androidTest/java/com/bw/phonecontact/ExampleInstrumentedTest.java
    }
}
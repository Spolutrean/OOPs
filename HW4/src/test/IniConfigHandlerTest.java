package test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import code.ConfigHandlerException;
import code.IniConfigHandler;

public class IniConfigHandlerTest {
    private static String commonPath = "/home/spolutrean/IdeaProjects/HW4/src/test/configs/";

    @Test
    public void checkGoodIni() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "good.ini");
        assertEquals(5000, (int) handler.getValue("COMMON", "StatisterTimeMs", Integer.class));
        assertEquals(9000000.0, handler.getValue("ADC_DEV", "SampleRate", Double.class), 1e-5);
        assertEquals("/sata/panorama", handler.getValue("COMMON", "DiskCachePath", String.class));
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkWringFileExtension() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "good");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkNotExistsFile() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "notExists.ini");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkSectionDescribeTwice() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "sectionTwice.ini");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkInvalidSectionName() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "invalidSectionName.ini");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkInvalidKeyValueString() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "badKeyValue.ini");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkInvalidKey() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "badKey.ini");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkCopyKeys() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "copyOfKey.ini");
    }

    @Test(expected = ConfigHandlerException.class)
    public void checkWithoutSection() throws ConfigHandlerException {
        IniConfigHandler handler = new IniConfigHandler(commonPath + "withoutSection.ini");
    }
}

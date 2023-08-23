package Data;

import org.testng.annotations.DataProvider;

public class TextBoxPageData {
    @DataProvider(name = "validNames")
    public static Object[][] validNames() {
        return new Object[][]{
                {"John"},
                {"John Doe"},
                {"123"},
                {"!@#$_="},
                {"Иван"},
                {"1,2+2.3-3*4/5"},
                {" "},
                {"    "},
                {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut"},
                {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut" +
                        " laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation " +
                        "ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor " +
                        "in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at " +
                        "vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore " +
                        "te feugait nulla facilisi.Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod " +
                        "tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation " +
                        "ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in " +
                        "hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at " +
                        "vero eros et accumsan et iusto odio dignissim qu"},
                {"select * from"}
        };
    }

    @DataProvider(name = "validMails")
    public static Object[][] validMails() {
        return new Object[][]{
                {"john@doe.com"},
                {"j@d.com.uk"},
                {"123@doe.ru"},
                {"123@321.ru"}
        };
    }

    @DataProvider(name = "invalidMails")
    public static Object[][] invalidMails() {
        return new Object[][]{
                {"john@doe.comm"},
                {"john@doe.c"},
                {"12 34@321.com"},
                {"123@321.c om"},
                {"!23@321.com"},
                {"123@32!.com"},
                {"123@321.c0m"},
                {"джон@doe.com"},
                {"john@доу.com"},
                {"john@doe.ком"},
                {"john@doe.o_O"},
                {"123@123.123"},
                {"jo-hn@№o_e.ru"},
                {"John"},
                {"@doe.comm"},
                {"john.comm"},
                {"!@#$_="},
                {"john@doe"},
                {"12345"},
                {" "},
                {"     "},
                {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut"},
                {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut" +
                        " laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation " +
                        "ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor " +
                        "in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at " +
                        "vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore " +
                        "te feugait nulla facilisi.Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod " +
                        "tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation " +
                        "ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in " +
                        "hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at " +
                        "vero eros et accumsan et iusto odio dignissim qu"},
                {"select * from"}
        };
    }
    @DataProvider(name = "Spaces")
    public static Object[][] textWithSpaces() {
        return new Object[][]{
                {" john@doe.com"},
                {"john@doe.com "}
        };
    }
}
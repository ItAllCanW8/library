package by.epamtc.library.controller.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag that prevents cross-site scripting(XSS).
 *
 * @author Artur Mironchik
 */
public class XSSProtectionTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String NEW_LINE = "\n";
    private static final String NEW_LINE_HTML_SYMBOL = "<br>";

    private static final String SPACE = "\\s";
    private static final String SPACE_HTML_SYMBOL = "&nbsp";

    private static final String GREATER_THAN = ">";
    private static final String GREATER_THAN_HTML_SYMBOL = "&gt";

    private static final String LESS_THAN = "<";
    private static final String LESS_THAN_HTML_SYMBOL = "&lt";

    private static final String LEFT_PARENTHESIS = "(";
    private static final String LEFT_PARENTHESIS_HTML_SYMBOL = "&#40";

    private static final String RIGHT_PARENTHESIS = ")";
    private static final String RIGHT_PARENTHESIS_HTML_SYMBOL = "&#41";

    private static final String SINGLE_QUOTE = "'";
    private static final String SINGLE_QUOTE_HTML_SYMBOL = "&#39";

    private static final String QUOTATION = "\"";
    private static final String QUOTATION_HTML_SYMBOL = "&quot";
    private String text;

    /**
     * Setter method of text.
     *
     * @param text String object
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() throws JspException {
        String result = text
                .replace(GREATER_THAN, GREATER_THAN_HTML_SYMBOL)
                .replace(SPACE, SPACE_HTML_SYMBOL)
                .replace(QUOTATION, QUOTATION_HTML_SYMBOL)
                .replace(LESS_THAN, LESS_THAN_HTML_SYMBOL)
                .replace(LEFT_PARENTHESIS, LEFT_PARENTHESIS_HTML_SYMBOL)
                .replace(RIGHT_PARENTHESIS, RIGHT_PARENTHESIS_HTML_SYMBOL)
                .replace(SINGLE_QUOTE, SINGLE_QUOTE_HTML_SYMBOL)
                .replace(NEW_LINE, NEW_LINE_HTML_SYMBOL);
        try {
            pageContext.getOut().write(result);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error displaying text ", e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}

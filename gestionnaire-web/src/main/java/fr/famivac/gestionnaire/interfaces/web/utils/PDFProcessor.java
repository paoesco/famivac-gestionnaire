package fr.famivac.gestionnaire.interfaces.web.utils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named(value = "PDFProcessor")
@ViewScoped
public class PDFProcessor implements Serializable {

	private static final long serialVersionUID = -4516484884799503473L;

	public void preProcess(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.A4.rotate());
        pdf.open();
    }

}

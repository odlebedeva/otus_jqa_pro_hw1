package pojo.xml.com.dataaccess.webservicesserver;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
  public ObjectFactory() {}
  public NumberToWords createNumberToWords() {
    return new NumberToWords();
  }
  public NumberToWordsResponse createNumberToWordsResponse() {
    return new NumberToWordsResponse();
  }
  public NumberToDollars createNumberToDollars() {
    return new NumberToDollars();
  }
  public NumberToDollarsResponse createNumberToDollarsResponse() {
    return new NumberToDollarsResponse();
  }
}

package basic.version6;


public class ViewStern  implements View {

  private ZahlEinfach model;

  public ViewStern( ZahlEinfach model ) {
    this.model = model;
  }

  public void ausgabe() {
    StringBuilder sb = new StringBuilder( model.getZahl() );
    for ( int i = 0; i < model.getZahl(); ++i ) {
      sb.append( '*' );
    }
    System.out.println( sb );
  }

}
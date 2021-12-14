/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import ClasesSistema.Compras.Articulo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author DIRECCION
 */
public class ImprimirTickets{
    
    private Ticket ticket;

    public void Imprimir(String ruta) throws IOException, DocumentException {
        try (FileOutputStream file = new FileOutputStream(ruta+File.separator+"Ticket"+ticket.getFechaHora().getFechaHora()+".pdf")) {
            Document documento = new Document();
            PdfWriter.getInstance(documento, file);
            
            Paragraph parrafo = new Paragraph("ABARROTES TIZIMIN",       
                        FontFactory.getFont("arial",
                        22,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        ));
            parrafo.setAlignment(1);
            
            
            // aqui añades dlos parrafos al PDF  
            Paragraph nombredueño= new Paragraph("Nombre del comprador: "+ticket.getCompras().getCliente().getNom()+" "+ticket.getCompras().getCliente().getAp(),
                        FontFactory.getFont("Courier",
                                13,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            nombredueño.setAlignment(1);
            Paragraph direccion= new Paragraph("CALLE "+ticket.getCompras().getCliente().getAddress().getCalle()+"N "+ticket.getCompras().getCliente().getAddress().getNumero(),
                        FontFactory.getFont("Courier",
                            12,
                            Font.BOLDITALIC,
                            BaseColor.BLACK));
            direccion.setAlignment(1);
            Paragraph datos= new Paragraph("COL. "+ticket.getCompras().getCliente().getAddress().getColonia()+"  CP. "+ticket.getCompras().getCliente().getAddress().getCP(),
                         FontFactory.getFont("Courier",
                                12,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            datos.setAlignment(1);
    
            Paragraph total= new Paragraph(" \nTOTAL: $"+ticket.getCompras().getImporte(),
                        FontFactory.getFont("Courier",
                                12,
                                Font.STRIKETHRU,
                                BaseColor.BLACK));
           total.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pago= new Paragraph("PAGO: $"+ticket.getCompras().getPagoCliente(),
                        FontFactory.getFont("Courier",
                                12,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            pago.setAlignment(Element.ALIGN_RIGHT);
            Paragraph cambio= new Paragraph("CAMBIO: $"+ticket.getCompras().getCambio(),
                        FontFactory.getFont("Courier",
                                12,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            cambio.setAlignment(Element.ALIGN_RIGHT);
           
            Paragraph nota= new Paragraph(" \nESTE NO ES UN COMPROBANTE FISCAL",
                        FontFactory.getFont("Courier",
                                12,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            nota.setAlignment(1);
            Paragraph gracias= new Paragraph(" \nGRACIAS POR SU COMPRA, REGRESE PRONTO.",
                         FontFactory.getFont("Courier",
                                12,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            gracias.setAlignment(1);
            Paragraph recordarnom= new Paragraph(" \n\n Abarrotes Tizimín                   Fecha y hora "+ticket.getFechaHora().getFechaHora(),
                        FontFactory.getFont("Courier",
                                10,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
            recordarnom.setAlignment(1);
            
            
            // aqui añades dlos parrafos al PDF
            documento.open();
            documento.add(parrafo);
            documento.add(nombredueño);
            documento.add(direccion);
            documento.add(datos);
          
            documento.add(new Paragraph("=========================================================================="));
            ArrayList<Articulo> articulos=ticket.getCompras().getListaCompra();
            
            for(Articulo art: articulos){
                Paragraph codigoproducto= new Paragraph(art.getCodigo(),
                        FontFactory.getFont("arial",
                                12,
                                Font.BOLDITALIC,
                                BaseColor.BLACK));
                codigoproducto.setAlignment(0);
                Paragraph nomproducto= new Paragraph(art.getNombre(),
                            FontFactory.getFont("Courier",
                                    12,
                                    Font.BOLDITALIC,
                                    BaseColor.BLACK));
                nomproducto.setAlignment(0);
                Paragraph precios= new Paragraph("CANTIDAD:     "+art.getCant()+"          PRECIO: "+art.getPrecio()+"            SUBTOTAL: "+art.getSubtotal(),
                            FontFactory.getFont("Courier",
                                    12,
                                    Font.BOLDITALIC,
                                    BaseColor.BLACK));
                precios.setAlignment(0);
            
            
            documento.add(codigoproducto);
            documento.add(nomproducto);
            documento.add(precios);
            }
            documento.add(total);
            documento.add(pago);
            documento.add(cambio);
           
            documento.add(nota);
            documento.add(gracias);
            documento.add(recordarnom);
            documento.close();
            //file.close();

            
            String contenido = ticket.getCompras().getImporte()+"";
            
            parrafo.setAlignment(1);

        }
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
}

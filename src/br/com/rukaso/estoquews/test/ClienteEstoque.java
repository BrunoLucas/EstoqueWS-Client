package br.com.rukaso.estoquews.test;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.rukaso.estoquews.cliente.EstoqueWS;
import br.com.rukaso.estoquews.cliente.EstoqueWS_Service;
import br.com.rukaso.estoquews.cliente.Filtro;
import br.com.rukaso.estoquews.cliente.Filtros;
import br.com.rukaso.estoquews.cliente.Item;
import br.com.rukaso.estoquews.cliente.ListaItens;
import br.com.rukaso.estoquews.cliente.TipoItem;

public class ClienteEstoque{

    public static void main(String[] args) throws Exception {

    	EstoqueWS cliente = new EstoqueWS_Service().getEstoqueWSImplPort();

    	Filtro filtro = new Filtro();
    	filtro.setNome("IPhone");
    	filtro.setTipo(TipoItem.CELULAR.name());

    	Filtros filtros = new Filtros();
    	filtros.getFiltro().add(filtro);

    	ListaItens lista = cliente.todosOsItens(filtros);

    	System.out.println("Resposta do serviço: " + lista);
    	
        URL url = new URL("http://localhost:8080/EstoqueWS-Web/EstoqueWS/EstoqueWSImpl?wsdl");
        QName qname = new QName("http://ws.estoque.caelum.com.br/", "EstoqueWS");

        Service service = Service.create(url, qname);

        EstoqueWS cliente2 = service.getPort(EstoqueWS.class);

        Filtro filtro2 = new Filtro();
        filtro2.setNome("IPhone");
        filtro2.setTipo(TipoItem.CELULAR.name());;

        filtros =new Filtros();
        filtros.getFiltro().add(filtro);
        ListaItens lista2 = cliente.todosOsItens(filtros);

        for (Item item : lista.getItem()) {
            System.out.println(item.getNome());
        }
    }
}
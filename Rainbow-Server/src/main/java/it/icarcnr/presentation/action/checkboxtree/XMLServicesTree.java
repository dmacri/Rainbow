package it.icarcnr.presentation.action.checkboxtree;

import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.generated.ServiceNodeSource;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;

import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLServicesTree {

	public static Document getXmlTree(final List<Service> serviceList, Integer serviceNodeId/*IChartsConstants.ChartMode chartMode*/) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		Element root = doc.createElement("servicesTree");
		doc.appendChild(root);

		if(serviceList!=null){
			String title = serviceNodeId!=null?"Servizio": "Nodi";

			Boolean disabled = Boolean.FALSE;
			String qTip = "services";
			String id = "nodes";
			Boolean checked = null;
			String iconCls = "node-icon";
			String type = "services";
			Element element = makeElement(doc, type, id, title, iconCls, qTip, checked, disabled,null);
			root.appendChild(element);
			addServices(doc,element,serviceList,serviceNodeId);
		}
		return doc;
	}

	private static void addServices(Document doc, Element root, List<Service> serviceList, /*ChartMode chartMode*/Integer serviceNodeId) {
		for (Service service : serviceList) {
			StringBuilder title;
			StringBuilder sourceNode=new StringBuilder();


			Set<ServiceNodeSource> nodeSources = service.getServiceNodeSources();
			boolean moreSources = false;
			for (ServiceNodeSource serviceNodeSource : nodeSources) {
				if(moreSources){
					sourceNode.append(", ");
				}
				sourceNode.append(serviceNodeSource.getNode().getDescription()); //LB PROXY Roma CTRL
				moreSources = true;
			}



			if(serviceNodeId!=null){
				String requestDescription=service.getRequest().getDescription();
				if(service.getNodeByIdNode()==service.getNodeByIdReceiver()){
					

					title = new StringBuilder(requestDescription);
					if(!service.getNodeByIdNode().getDescription().equals(sourceNode.toString())){
						title.append(" (");
						title.append(sourceNode);
						title.append(")");
					}

				}else{

					title = new StringBuilder(requestDescription);
					title.append(" verso ");
					title.append(service.getNodeByIdReceiver().getDescription());
					if(!service.getNodeByIdNode().getDescription().equals(sourceNode.toString())){
						title.append(" (");
						title.append(sourceNode);
						title.append(")");
					}
					
					
//					if(!nodeDescription.equals(sourceNode.toString())&& !sourceNode.toString().equals(service.getNodeByIdReceiver().getDescription())){
//						title.append(" (");
//						title.append(sourceNode);
//						title.append(" &#8594; ");
//						title.append(service.getNodeByIdReceiver().getDescription());
//						title.append(")");
//
//					}else{
//						title.append(" (");
//						title.append(sourceNode);
//						title.append(")");
//						
//					}
				}
			}else{
				String nodeDescription = service.getNodeByIdNode().getDescription();
				if(service.getNodeByIdNode()==service.getNodeByIdReceiver()){

					title = new StringBuilder(nodeDescription);
					if(!nodeDescription.equals(sourceNode.toString())){
						title.append(" (");
						title.append(sourceNode);
						title.append(")");
					}

				}else{
					title = new StringBuilder(nodeDescription);
					title.append(" &#8594; ");
					title.append(service.getNodeByIdReceiver().getDescription());
					
					if(!nodeDescription.equals(sourceNode.toString())){
						title.append(" (");
						title.append(sourceNode);
						title.append(")");
					}
					
				}



			}


			Boolean disabled = Boolean.FALSE;
			String qTip = null;
			String id = "service"+service.getId();
			Boolean checked = Boolean.FALSE;
			//			String iconCls = "service-status-icon";
			String iconCls = "node-icon";;
			String type = "service";
			Element element = makeElement(doc, type, id, title.toString(), iconCls, qTip, checked, disabled,service.getId());
			root.appendChild(element);
		}

	}

	private static Element makeElement(Document doc, String type, String id, String title, String iconCls, String qTip, Boolean checked, Boolean disabled, Integer serviceId){
		Element element = doc.createElement(type);
		element.setAttribute("id",id);
		element.setAttribute("title",title);
		element.setAttribute("iconCls", iconCls);
		element.setAttribute("qTip", qTip);
		if(checked!=null){
			element.setAttribute("checked", checked.toString());
		}
		if(disabled!=null){
			element.setAttribute("disabled", disabled.toString());
		}
		element.setAttribute("expanded", Boolean.TRUE.toString());

		if(serviceId!=null){
			element.setAttribute(IParameterHttpServletRequestContants.SERVICE_ID, serviceId.toString());
		}

		return element;
	}

}

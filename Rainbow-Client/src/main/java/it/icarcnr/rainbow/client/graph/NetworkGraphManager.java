
package it.icarcnr.rainbow.client.graph;

import com.google.gwt.core.client.JavaScriptObject;

public class NetworkGraphManager {

	public native JavaScriptObject getConfig( boolean isInternetExplorer, String name, String bgc, int w,
			int h, String firstTooltipMessage, String secondTooltipMessage ) /*-{
		var CRITICAL_COLOUR = "#F16243";
		var MAJOR_COLOUR = "#F6CC3A";
		var NORMAL_COLOUR = "#ACD373";
		var SUSPENDED_COLOUR = "#C2C2C2";

		var NODE_COLOUR = '#fff';
		var NODE_BORDER_COLOUR = '#666';
//		var NODE_BORDER_COLOUR = '#444';
		var NODE_SELECTED_COLOUR = '#CCE6FF';

		var EDGE_COLOUR = '#bbbbbb';
		var EDGE_SELECTED_COLOUR = '#44d';

		return {
		//id of viz container element
		injectInto: name,
		//set backgroundColor of viz container element
		backgroundColor: bgc,
		//set width of viz container element
		width: w,
		//set height of viz container element
		height: h,
		//set duration of the animation in milliseconds
		//		duration: 800,
		// set frames per second
		//		fps: 25,
		//set animation transition type
		transition: $wnd.$jit.Trans.Quart.easeInOut,
		//set distance between node and its children
		levelDistance: 50,
		//sibling and subtrees offsets
		    siblingOffset: 40,
		    subtreeOffset: 40,
		//	//enable panning
		Navigation: {
			enable:true,
			panning:true
//			,
//			zooming: 10  
		},

		//set node and edge styles
		//set overridable=true for styling individual
		//nodes or edges
		Node: {
		    overridable: true,
		    type: 'stroke-rect',
		    height: 32,
		//			    width: 160,
		    width: 130,
		    //canvas specific styles
		    CanvasStyles: {
		      fillStyle: NODE_COLOUR,
		      strokeStyle: NODE_BORDER_COLOUR,
		      lineWidth: 1
		    }
		},

		Edge: {
			type: 'bezier',
			color: EDGE_COLOUR,
			overridable: true
		},

//		Tips: {
//			enable: true,
////			type: 'Native',
//			offsetX: 10,
//			offsetY: 10,
//			onShow: function(tip, node) {
//				var style = tip.style;
//				
//				style.fontStyle = 'normal';
//				style.fontWeight = '400';
//				style.fontFamily = 'tahoma,arial,helvetica,sans-serif';
//				style.fontSize =  '10px';
//				style.background = 'url(js/ext/resources/images/default/qtip/tip-sprite.gif) no-repeat 0';
//				style.border = '1px solid #8EAACE';
//				style.color = '#444';
//				style.paddingTop = '5px';
//				style.paddingBottom = '5px';
//				style.paddingLeft = '5px';
//				style.paddingRight = '5px';
//				style.overflow = 'hidden';
//				style.zIndex = '20000';
//				style.zoom = '1';
//				
//				
////				style.background = 'transparent url(../images/galdaka/qtip/tip-sprite.gif) no-repeat 0 -12px';
////				tip.style = '';
////				tip.setAttributeNS('ext', 'ext:qtitle', 'ciao');
////				tip.setAttributeNS('ext', 'ext:qtip', 'ciao');
////				tip.Attributes.Add('ext:qtitle=','ciao');
////				tip.Attributes.Add('ext:qtip=',' cc');
////				tip.innerHTML = '<i>'+ 'Left click to explode '+node.name + '<br>Right click to view '+node.name + ' services status</i>';
//
//				tip.innerHTML = '<b>'+node.data.fullName+'</b><br>'+ 'Left click to explode node<br>Right click to view services status';
//				
//			}
//		},

		//			Events: {  
		//				enable: true,  
		//				onClick: function(node, eventInfo, e) {
		////					jitWrapperForwardEvent(name, node, eventInfo, e); 
		//					jitWrappedObject(name).onClick(node.id);
		//				},
		//				onRightClick: function(node, eventInfo, e) {
		////					jitWrapperForwardEvent(name, node, eventInfo, e); 
		//				}
		//			},

		onBeforePlotNode: function(node){

		    if (node.selected) {
		    	node.setCanvasStyle('fillStyle', NODE_SELECTED_COLOUR);
		    } else {
		    	node.setCanvasStyle('fillStyle', NODE_COLOUR);
		    }

		    if(typeof node.data.status == "undefined"){

		    }else if(node.data.status == "suspended"){
		    	node.setData('statusColor',SUSPENDED_COLOUR);
		    }else if(node.data.status == "normal"){
		    	node.setData('statusColor',NORMAL_COLOUR);
		    }else if(node.data.status == "major"){
				node.setData('statusColor',MAJOR_COLOUR);
			}else if(node.data.status == "critical"){
				node.setData('statusColor',CRITICAL_COLOUR);
			}

		  	if(typeof node.data.childrenStatus == "undefined"){

		    }else if(node.data.childrenStatus == "suspended"){
		    	node.setData('childrenStatusColor',SUSPENDED_COLOUR);
		    }else if(node.data.childrenStatus  == "normal"){
		    	node.setData('childrenStatusColor', NORMAL_COLOUR);
		    }else if(node.data.childrenStatus == "major"){
				node.setData('childrenStatusColor', MAJOR_COLOUR);
			}else if(node.data.childrenStatus == "critical"){
				node.setData('childrenStatusColor',CRITICAL_COLOUR);
			}

		},

		onBeforeCompute: function(node){
			var beforeComputeForwardEvent = function() {
				jitWrapperForwardComputeEvent(name, {type: 'beforeCompute'}, node); 
			};
			beforeComputeForwardEvent(); 
		},  

		onAfterCompute: function(){ 
			var afterComputeForwardEvent = function() {
				jitWrapperForwardComputeEvent(name,{type: 'afterCompute'}, {}); 
			};
			afterComputeForwardEvent(); 
		},  

		//This method is called on DOM label creation.
		//Use this method to add event handlers and styles to
		//your node.
		onCreateLabel: function(label, node) {
			label.id = node.id;
			label.setAttribute('ext:qtitle',node.data.fullName);
//						label.setAttribute('ext:qtip',firstTooltipMessage+'Left click to explode node<br>Right click to view services status');
			label.setAttribute('ext:qtip',firstTooltipMessage+'<br>'+secondTooltipMessage);
			label.innerHTML = '<table '+
							'style="dipslay: inherit; width: 90px; height: 32px; '+
							'vertical-align: middle; text-align: center; '+
							'font-family: tahoma,arial,helvetica,sans-serif; font-style = normal; font-weight = 400; font-size: 10px; ">'+
//							'<tr><td ext:qtitle = "'+node.data.fullName+'" ext:qtip= "Left click to explode node<br>Right click to view services status" >'+node.name+'</td></tr></table>';
							'<tr><td ext:qtitle = "'+node.data.fullName+'" ext:qtip= "'+firstTooltipMessage+'<br>'+secondTooltipMessage+'" >'+node.name+'</td></tr></table>';


//			label.innerHTML = '<p '+
//							'style="dipslay: inherit; width: 80px; height: 32px; '+
//							'vertical-align: middle; text-align: center; '+
//							'fontFamily: tahoma,arial,helvetica,sans-serif; font-style = normal; font-weight = 400; font-size: 9px; ">'+
//							node.name+'</p>';

//			label.innerHTML = node.name;

//			label.innerHTML = node.name;

			var forwardEvent = function(event) {
		    	jitWrapperForwardEvent(name, event || $wnd.event, label, node); };
			label.onclick = function(event) {
		   		jitWrappedObject(name).onClick(node.id);
		   		forwardEvent(event); 
		   	};
		   	label.oncontextmenu = forwardEvent;
		   	label.onmouseover = forwardEvent;
			label.onmouseout = forwardEvent;
			var style = label.style;

			if(isInternetExplorer){
				style.width = '130px';
				style.height ='32px';
			}else{
				style.width = '90px';
				style.height ='32px';
			}

			style.paddingLeft = '32px';
			style.paddingRight= '8px';

//			style.display = 'block';
//			style.verticalAlign= 'middle';
//			style.textAlign =  'center';

			style.cursor = 'pointer';
			style.color = '#333';
//			style.fontStyle = 'normal';
//			style.fontWeight = '400';
//			style.fontFamily = 'tahoma,arial,helvetica,sans-serif';
//			style.fontSize =  '9px';

			if(node.data.image){
				style.backgroundImage = node.data.image;
				style.backgroundRepeat = 'no-repeat';
		//					style.backgroundPosition = 'left top';
				style.backgroundPosition = '8';
			}

		},


		onBeforePlotLine: function(adj){
			if (adj.nodeFrom.selected && adj.nodeTo.selected) {
				adj.data.$color = EDGE_SELECTED_COLOUR;
				adj.data.$lineWidth = 1.6;
			} else {
				delete adj.data.$color;
				delete adj.data.$lineWidth;
			}
		}
		};
	}-*/;

	public native void loadAndDisplay(String name, JavaScriptObject json, String nodeIdToExpand ) /*-{
		//load json data
		var st = jitWrappedObject(name);
		st.loadJSON(json);
		//compute node positions and layout
		st.compute();
		//optional: make a translation of the tree
		st.geom.translate(new $wnd.$jit.Complex(-200, 0), "current");
		//	Emulate a click on the root node.  
		//	st.onClick(st.root); 
		st.onClick( nodeIdToExpand || (st.clickedNode && st.clickedNode.id) || st.root);
//        var nodeToExpand = st.graph.getNode(nodeIdToExpand);
//		st.onClick(node);
//		st.onClick("3_1_184");
//		st.onClick(nodeIdToExpand);
	}-*/;

	public native void selectNode(String jitName, String nodeIdToExpand) /*-{
		var st = jitWrappedObject(jitName);
		st.onClick( nodeIdToExpand );
	}-*/;

}

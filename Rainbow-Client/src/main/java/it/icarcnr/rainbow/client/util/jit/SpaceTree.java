package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.core.client.JavaScriptObject;

public class SpaceTree extends JitWidget {

	public SpaceTree(String name, JavaScriptObject config) {
		super(name, config);
	}

	@Override
	protected native JavaScriptObject init(JavaScriptObject config) /*-{	
		//Create a node rendering function that plots a fill
		//rectangle and a stroke rectangle for borders
		$wnd.$jit.ST.Plot.NodeTypes.implement({
			'stroke-rect': {
				'render': function(node, canvas) {
					var width = node.getData('width'),
					height = node.getData('height'),
					pos = this.getAlignedPos(node.pos.getc(true), width, height),
					posX = pos.x + width/2,
					posY = pos.y + height/2;
					ctx = canvas.getCtx();
					
					this.nodeHelper.rectangle.render('fill', {x: posX, y: posY}, width, height, canvas);
					this.nodeHelper.rectangle.render('stroke', {x: posX, y: posY}, width, height, canvas);
					
					if(typeof node.data.status == "undefined"){
					}else{
						ctx.lineWidth = 0;
						ctx.fillStyle = node.getData('statusColor');
						this.nodeHelper.rectangle.render('fill', {x: posX - width/2 + 4, y: posY}, 6, height - 1, canvas);
					}
					
					if(typeof node.data.childrenStatus == "undefined"){
					}else{
						ctx.lineWidth = 0;
						ctx.fillStyle = node.getData('childrenStatusColor');
						this.nodeHelper.rectangle.render('fill', {x: posX + width/2 - 4 , y: posY}, 6, height - 1, canvas);
					
					}
					//          this.nodeHelper.rectangle.render('stroke', {x: posX-4, y: posY}, width-4, height-6, canvas);
					//          this.nodeHelper.rectangle.render('stroke', {x: posX+4, y: posY}, width-4, height-6, canvas);
				}
			}
    	});

		var st = new $wnd.$jit.ST(config);
		return st;
	}-*/;
	
	
	private native void initJavaSciptUserAgent()/*-{
		var labelType, useGradients, nativeTextSupport, animate;
	
		(function() {
			var ua = navigator.userAgent,
			iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
			typeOfCanvas = typeof HTMLCanvasElement,
			nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
			textSupport = nativeCanvasSupport && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
			//I'm setting this based on the fact that ExCanvas provides text support for IE
			//and that as of today iPhone/iPad current text support is lame
			labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
			nativeTextSupport = labelType == 'Native';
			useGradients = nativeCanvasSupport;
			animate = !(iStuff || !nativeCanvasSupport);
		})();
	
		var Log = {
			elem: false,
			write: function(text){
				if (!this.elem) 
				this.elem = document.getElementById('log');
				this.elem.innerHTML = text;
				this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
			}
		};
	}-*/;

}

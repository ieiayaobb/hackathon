var labelType, useGradients, nativeTextSupport, animate;

(function () {
    var ua = navigator.userAgent,
        iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
        typeOfCanvas = typeof HTMLCanvasElement,
        nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
        textSupport = nativeCanvasSupport
            && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
    //I'm setting this based on the fact that ExCanvas provides text support for IE
    //and that as of today iPhone/iPad current text support is lame
    labelType = (!nativeCanvasSupport || (textSupport && !iStuff)) ? 'Native' : 'HTML';
    nativeTextSupport = labelType == 'Native';
    useGradients = nativeCanvasSupport;
    animate = !(iStuff || !nativeCanvasSupport);


})();

$jit.ForceDirected.Plot.NodeTypes.implement({
    //// this node type is used for plotting resource types (web)
    'building1': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building1.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building2': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building2.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building3': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building3.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building4': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building4.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building5': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building5.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building6': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building6.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building7': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building7.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building8': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building8.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
    'building9': {
        'render': function (node, canvas) {
            var ctx = canvas.getCtx();
            var img = new Image();
            var pos = node.getPos();
            img.onload = function () {
                ctx.drawImage(img, pos.x - 60, pos.y - 60);
            }
            img.src = '../static/images/building/building9.png';
        },
        'contains': function (node, pos) {
            var npos = node.pos.getc(true),
                dim = node.getData('dim');
            return this.nodeHelper.square.contains(npos, pos, dim);
        }
    },
});

function init(json) {
    // init ForceDirected
    var fd = new $jit.ForceDirected({
        //id of the visualization container
        injectInto: 'infovis',
        //Enable zooming and panning
        //by scrolling and DnD
        Navigation: {
            enable: true,
            //Enable panning events only if we're dragging the empty
            //canvas (and not a node).
            panning: 'avoid nodes',
            zooming: 10 //zoom speed. higher is more sensible
        },
        // Change node and edge styles such as
        // color and width.
        // These properties are also set per node
        // with dollar prefixed data-properties in the
        // JSON structure.
        Node: {
            overridable: true,
            dim: 20
        },
        Edge: {
            overridable: true,
            color: '#8cc63e',
            lineWidth: 2,
            type: "arrow"
        },
        //Native canvas text styling
        Label: {
            type: 'HTML', //Native or HTML
            size: 20,
            color: "#000000",
            style: 'bold'
        },
        //Add Tips
        Tips: {
            enable: true,
            onShow: function (tip, node) {
                //count connections
                var count = 0;
                node.eachAdjacency(function () {
                    count++;
                });
                //display node info in tooltip
                tip.innerHTML = "<div class=\"tip-title\">" + node.name + "</div>"
                + "<div class=\"tip-text\"><b>competitors:</b> " + count + "</div>";
            }
        },
        // Add node events
        Events: {
            enable: true,
            type: 'Native',
            //Change cursor style when hovering a node
            onMouseEnter: function () {
                fd.canvas.getElement().style.cursor = 'move';
            },
            onMouseLeave: function () {
                fd.canvas.getElement().style.cursor = '';
            },
            //Update node positions when dragged
            onDragMove: function (node, eventInfo, e) {
                var pos = eventInfo.getPos();
                node.pos.setc(pos.x, pos.y);
                fd.plot();
            },
            //Implement the same handler for touchscreens
            onTouchMove: function (node, eventInfo, e) {
                $jit.util.event.stop(e); //stop default touchmove event
                this.onDragMove(node, eventInfo, e);
            },
            //Add also a click handler to nodes
            onClick: function (node) {
                if (!node) return;
                window.location.href = "http://localhost:8080/compete/tree/" + node.name;
            }
        },
        //Number of iterations for the FD algorithm
        iterations: 200,
        //Edge length
        levelDistance: 100,
        // Add text to the labels. This method is only triggered
        // on label creation and only for DOM labels (not native canvas ones).
        onCreateLabel: function (domElement, node) {
            domElement.innerHTML = node.name;
            var style = domElement.style;
            style.fontSize = "24px";
            style.color = "#ddd";
        },
        // Change node styles when DOM labels are placed
        // or moved.
        onPlaceLabel: function (domElement, node) {
            var style = domElement.style;
            var left = parseInt(style.left);
            var top = parseInt(style.top);
            var w = domElement.offsetWidth;
            style.left = (left - w / 2) + 'px';
            style.top = (top - 15) + 'px';
            style.display = '';
        }
    });
    // load JSON data.
    fd.loadJSON(json);
    // compute positions incrementally and animate.
    fd.computeIncremental({
        iter: 40,
        property: 'end',
        onStep: function (perc) {
        },
        onComplete: function () {
            fd.animate({
                modes: ['linear'],
                transition: $jit.Trans.Elastic.easeOut,
                duration: 2500
            });
        }
    });
    // end
}

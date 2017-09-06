
/**
 * Extract map HTML from the page
 */
export const getMapElementProperties = (options) => {
  let maps = document.getElementsByClassName("map");
  if (options !== undefined) {
    // only print the main map
    if (options.right === true) {
      maps = [maps[1]];
    }
    // only print the comparison map
    if (options.left === true && maps.length === 2) {
      maps = [maps[0]];
    }
  }
  
  let outerHTML = "<div class=\"map-layout\">",
    clientWidth = 0,
    clientHeight;
  
  if (maps.length === 2) {
    outerHTML = "<div class=\"map-layout compare-layout\">";
  }
  
  for (let mapElement of maps) {
    const mapNode = processMapNode(mapElement);
    
    outerHTML += mapNode.outerHTML;
    clientWidth += mapElement.clientWidth;
    clientHeight = mapElement.clientHeight;   // we don't need addition here, just get one of the map height
  }
  outerHTML += "</div>";
  
  return { outerHTML, clientWidth, clientHeight };
};

const processMapNode = (mapElement) => {
  const node = mapElement.cloneNode(true);
  node.getElementsByClassName("leaflet-control-zoom")[0].remove();
  
  return node;
};

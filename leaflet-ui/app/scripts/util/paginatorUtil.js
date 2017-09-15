
export const getActivePage = (eventKey, totalPages, number) => {
  //workaround for fix bootstrap paginator issues
  let activePage = 0;
  switch (eventKey.target.innerText) {
    case "»":
      
      activePage = totalPages - 1;
      break;
    case "›":
      
      activePage = number + 1;
      break;
    case "«":
      
      activePage = 0;
      break;
    case "‹":
      
      activePage = number - 1;
      break;
    default :
      
      activePage = parseInt(eventKey.target.innerHTML) - 1;
      break;
  }
  return activePage;
};

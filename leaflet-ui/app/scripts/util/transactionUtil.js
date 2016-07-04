

export const sumarizeValues=(transactions)=>{
  let actualCommitments = 0;
  let targetCommitments = 0;
  let cancelledCommitments = 0;
  let actualDisbursements = 0;
  let targetDisbursements = 0;
  let cancelledDisbursements = 0;
  let actualExpenditures = 0;
  let targetExpenditures = 0;
  let cancelledExpenditures = 0;
  transactions.map((transaction) => {
    let amount = parseInt(transaction.amount)
    switch(transaction.transactionType) {
      case "COMMITMENTS":
        switch(transaction.transactionStatus) {
          case "ACTUAL":
            actualCommitments += amount;
            break;
          case "TARGET":
            targetCommitments += amount;
            break;
          case "CANCELLED":
            cancelledCommitments += amount;
            break;
        }
        break;
      case "DISBURSEMENTS":
        switch(transaction.transactionStatus) {
          case "ACTUAL":
            actualDisbursements += amount;
            break;
          case "TARGET":
            targetDisbursements += amount;
            break;
          case "CANCELLED":
            cancelledDisbursements += amount;
            break;
        }
        break;
      case "EXPENDITURES":
        switch(transaction.transactionStatus) {
          case "ACTUAL":
            actualExpenditures += amount;
            break;
          case "TARGET":
            targetExpenditures += amount;
            break;
          case "CANCELLED":
            cancelledExpenditures += amount;
            break;
        }
        break;
    }
  });
  return {
    'actualCommitments': formatValue(actualCommitments),
    'targetCommitments': formatValue(targetCommitments),
    'cancelledCommitments': formatValue(cancelledCommitments),
    'actualDisbursements': formatValue(actualDisbursements),
    'targetDisbursements': formatValue(targetDisbursements),
    'cancelledDisbursements': formatValue(cancelledDisbursements),
    'actualExpenditures': formatValue(actualExpenditures),
    'targetExpenditures': formatValue(targetExpenditures),
    'cancelledExpenditures': formatValue(cancelledExpenditures)
  }

}

export const formatValue=(value)=>{
  let v;
  if (value==0){
    return "0";
  } else if (value>1000000000){
    v = (value/1000000000).toFixed(3);
    return ""+v+"B";
  } else if (value>1000000){
    v = (value/1000000).toFixed(3);
    return ""+v+"M";
  } else if (value>1000){
    v = (value/1000).toFixed(3);
    return ""+v+"K";
  } else {
    return ""+value;
  }
}
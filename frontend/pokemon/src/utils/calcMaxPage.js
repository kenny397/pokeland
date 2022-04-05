const calcMaxPage = (total, each) => {
  let maxPage = 1;
  const quotient = total / each;

  if (parseInt(quotient) === quotient) {
    maxPage = quotient;
  } else {
    maxPage = Math.floor(total / each) + 1;
  }

  return maxPage;
};

export default calcMaxPage;

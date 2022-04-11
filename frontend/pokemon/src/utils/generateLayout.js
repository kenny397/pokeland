const generateLayout = ({ isMobile, isTablet, isPc }) => {
  let columns = 2;

  if (isMobile) {
    columns = 2;
  } else if (isTablet) {
    columns = 4;
  } else if (isPc) {
    columns = 6;
  }

  return {
    each: columns * 3,
    gridTemplateColumns: '1fr '.repeat(columns).slice(0, -1),
    gridColumn: `${columns}/${columns + 1}`,
    gridTemplateRows: '1fr '.repeat(4).slice(0, -1),
  };
};

export default generateLayout;

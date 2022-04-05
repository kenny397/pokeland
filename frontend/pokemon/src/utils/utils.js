export const calcMaxPage = (total, each) => {
  let maxPage = 1;
  const quotient = total / each;

  if (parseInt(quotient) === quotient) {
    maxPage = quotient;
  } else {
    maxPage = Math.floor(total / each) + 1;
  }

  return maxPage;
};

export const generateLayout = ({ isMobile, isTablet, isPc }) => {
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

export const getImgPath = (id, option) => {
  const pokemonImgPath = `/images/pokemonImg/${option}/no.${id}_${option}.jpg`;
  return pokemonImgPath;
};

export const isLogin = () => {
  const jwt = localStorage.getItem('jwt');
  return jwt ? jwt : () => {alert('로그인이 필요합니다!'); return false;};
};

export const range = (start, end) => {
  let array = [];
  for (let i = start; i <= end; ++i) {
    array.push(i);
  }
  return array;
};

export const whatPageInPokedex = (pokedexId, each) => {
  return parseInt((pokedexId - 1) / each + 1);
};

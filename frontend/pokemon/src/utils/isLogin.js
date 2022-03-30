export default function isLogin() {
  const jwt = localStorage.getItem('jwt');
  return jwt ? jwt : () => {alert('로그인이 필요합니다!'); return false;};
}

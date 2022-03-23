import React from "react";
import { useNavigate } from "react-router-dom";
import { changeHeaderDisplay } from "../headerDisplay";

export default function IntroPage() {
  const navigate = useNavigate();
  
  const routerBtnClick = function () {
    changeHeaderDisplay('/support');
    navigate('/support');
  };
  return (
    <>
      <div>
        IntroPage
      </div>
      <div>
        <button className="submit-button" onClick={routerBtnClick}>main으로</button>
      </div>
    </>
  );
}

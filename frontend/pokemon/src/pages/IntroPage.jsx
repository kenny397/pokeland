import React from "react";
import { Link } from "react-router-dom";
import "./IntroPage.css";

export default function IntroPage() {
  return (
    <>
      <div>
        IntroPage
      </div>
      <div>
        <Link to="/main">
          <button className="submit-button">main으로</button>
        </Link>
      </div>
    </>
  );
}
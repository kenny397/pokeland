import React from "react";
import { useEffect } from "react";
import { changeHeaderDisplay } from "../headerDisplay";
import { Link } from 'react-router-dom';

export default function MainPage() {
  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div>
      <h1>
        MainPage
      </h1>
      <Link to="/support">
        <button>
          Support page
        </button>
      </Link>
    </div>
  );
}

import React from "react";
import { Link } from 'react-router-dom';

export default function MainPage() {
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

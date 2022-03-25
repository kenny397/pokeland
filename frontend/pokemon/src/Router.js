import React from "react";
import { useRoutes } from "react-router-dom";

import IntroPage from './pages/IntroPage';
import MainPage from './mainpage/MainPage';
import TutorialPage from './tutorial/TutorialPage';
import SupportPage from './support/SupportPage';
import PokedexPage from './pokedex/PokedexPage';
import NotFound from './pages/NotFound';
import Gacha from "./gacha/Gacha";

export const routers = [
  { path: "/", element: <IntroPage /> },
  { path: "/main", element: <MainPage /> },
  { path: "/tutorial", element: <TutorialPage /> },
  { path: "/support", element: <SupportPage /> },
  { path: "/pokedex", element: <PokedexPage /> },
  { path: "/gacha", element: <Gacha/> },
  { path: "*", element: <NotFound /> }
];

export const Router = () => {
  let element = useRoutes(routers);
  return element;
};

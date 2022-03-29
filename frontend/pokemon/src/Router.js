import React from "react";
import { useRoutes } from "react-router-dom";

import IntroPage from './intro/IntroPage';
import MainPage from './mainpage/MainPage';
import TutorialPage from './tutorial/TutorialPage';
import SupportPage from './support/SupportPage';
import PokedexPage from './pokedex/PokedexPage';
import NfpsByPokemon from './pokedex/NfpsByPokemon';
import NotFound from './pages/NotFound';
import GachaContainer from "./gacha/GachaContainer";

export const routers = [
  { path: "/", element: <IntroPage /> },
  { path: "/main", element: <MainPage /> },
  { path: "/tutorial", element: <TutorialPage /> },
  { path: "/support", element: <SupportPage /> },
  { path: "/pokedex", element: <PokedexPage /> },
  { path: "/pokedex/nfps/:pokedexId", element: <NfpsByPokemon /> },
  { path: "/gacha", element: <GachaContainer/> },
  { path: "*", element: <NotFound /> }
];

export const Router = () => {
  let element = useRoutes(routers);
  return element;
};

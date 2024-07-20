import logo from './taxlogo.jpg';
import {
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";
import './App.css';
import ViewTaxDetails from "./pages/ViewTaxDetails"
// import ViewTaxDetails from "./pages/ViewTaxDetails"
//import TaxHome from "./pages/TaxHome"

function App() {
  return (
    <Routes>
        {/* <Route path="/home" element={<TaxHome />} /> */}
        <Route
            path="/tax-home"
            element={<ViewTaxDetails />}
        />
        
    </Routes>
 
  );
}

export default App;

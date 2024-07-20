import React, { useEffect, useState } from 'react';
import {
    FormGroup,
    FormField,
    Button,
    Form,
    FormInput,
    Select,
    Divider,
    Modal
} from 'semantic-ui-react'
import { Calendar } from 'primereact/calendar';


export default function ViewTaxDetails() {

    const [municipalityName, setMunicipalityName] = useState('');
    const [municipalityTax, setmunicipalityTax] = useState('');
    const [taxDate, setTaxDate] = useState('');
    const [municipalitiesList, setMunicipalitiesList] = useState([])
    let resJson = {}
    let modalContent = ""

    const API_URL = process.env.REACT_APP_BACKEND_SERVER_URL;
   
    useEffect(() => {
        fetchMunicipalities();

    },[])
    
async function fetchMunicipalities(){

    const settings = {
        method: 'GET',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin' : 'http://localhost:3000',
            'Access-Control-Allow-Credentials': 'true'
        }
    };

    let municipalitiesJson = []

    try {
        let fetch_municipalities_response = await fetch(`${API_URL}/municipalities`, settings)
        if(fetch_municipalities_response.status === 200){
            resJson = await fetch_municipalities_response.json()
            console.log(resJson.data)

            municipalitiesList.splice(0,municipalitiesList.length)
            municipalitiesJson = resJson.data

            for(let i =0; i < municipalitiesJson.length; i++){
                municipalitiesList.push({"key": i+1, "text" : municipalitiesJson[i].municipalityName, "value" : municipalitiesJson[i].municipalityName})
            }
            setMunicipalitiesList(municipalitiesList)
            return municipalitiesList

        }
        else console.log("Error")

    } catch (err) {
        console.log(err)
    }
}

async function fetchMunicipalityTax(){

    const settings = {
        method: 'GET',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            // 'Access-Control-Allow-Origin' : 'http://localhost:3000',
            // 'Access-Control-Allow-Credentials': 'true'
        }
    };

    let taxValue = '0'
    

    try {
        let fetch_municipalities_response = await fetch(`${API_URL}/municipality/${municipalityName}/${taxDate}/tax-schedule`, settings)
        if(fetch_municipalities_response.status === 200){
            resJson = await fetch_municipalities_response.json()
            
            console.log(resJson.data)
            taxValue = resJson.data.tax
            setmunicipalityTax(taxValue)
            
            

        }
        else {
            setmunicipalityTax("Not Available")
            console.log("Error")}
            

    } catch (err) {
        console.log(err)
    }
}

    const handleSelectChange = (event) => {
        setMunicipalityName(event.target.innerText);
        console.log(event.target.innerText)
    };

    const setModalContent = () => {
        modalContent = "Test"
        return modalContent
    };

    const handleDateChange = (event) => {
        let date_temp = event.value
        var date = new Date(date_temp);
        console.log(date.toLocaleDateString("fr-CA"));

        setTaxDate(date.toLocaleDateString("fr-CA"));
        console.log(event.target.innerText)
    };


    return (
        <>
            <div className="App">

                <header className="App-header">
                    <h1>Tax Management Application</h1>

                    <Divider horizontal> </Divider>
                    <Form>
                        <div>

                        </div>
                        <FormGroup widths='equal'>

                            <FormField
                                control={Select}
                                options={municipalitiesList}
                                placeholder='Select Municipality'
                                onChange={handleSelectChange}
                                value={municipalityName}
                            />
 
                            <FormField 
                                control={Calendar}
                                // label='Date'
                                 onChange={handleDateChange} dateFormat="dd/mm/yy"
                                placeholder='Select Date'
                            />

                        </FormGroup>

                        <Divider horizontal> </Divider>
                        <Divider horizontal> </Divider>


                        <Modal
                            trigger={<Button content='View Tax Rate' onClick={fetchMunicipalityTax}  size='small' />}
                            header='Tax Rate Calculated!'
                            content={"The tax for the date "+ taxDate + " is " + municipalityTax}
                            actions={[{ key: 'done', content: 'Okay', positive: true }]}
                        />

                        <Divider horizontal> </Divider>

                    </Form>
                </header>
            </div>
        </>
    );
}
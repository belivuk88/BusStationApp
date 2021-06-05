import React from 'react';
import { Table, Button, Form, ButtonGroup} from "react-bootstrap";
import BusAxios from '../../apis/BusAxios';
import './../../index.css';



class Linija extends React.Component {

    constructor(props){
        super(props);
    
    let linija ={
        brojMesta: 0,
        cenaKarte: 0.00,
        destinacija: "",
        vremePolaska: "",
        prevoznikId: -1,
    }

    this.state = {
        linija: linija,
        linije:[],
        prevoznici:[],
        search: {destinacija: "", prevoznikId: -1, maxCenaKarte: 0.00},
        pageNo: 0,
        totalPages: 2,
        active: true,

    };

}

componentDidMount(){
    this.getData();
}

async getData(){
    await this.getPrevoznici();
    await this.getLinije(0);
}

async getLinije(page){
    let config = { params: {
        pageNo:page
    }};

    if(this.state.search.destinacija != ""){
        config.params.destinacija = this.state.search.destinacija;
    }
    if(this.state.search.prevoznikId != -1){
        config.params.prevoznikId = this.state.search.prevoznikId;
    }
    if(this.state.search.maxCenaKarte !=0.00){
        config.params.maxCenaKarte = this.state.search.maxCenaKarte;
    }
    try{
        let result = await BusAxios.get("/linije", config);
        if(result && result.status === 200){
            this.setState({
                pageNo: page,
                linije: result.data,
                totalPages: result.headers["total-pages"],
            });
        }
    }catch(error){
        alert("Nije uspelo dodavanje.")
    }
}

async getPrevoznici(){
    try{
        let result = await BusAxios.get("/prevoznici");
        if(result && result.status === 200){
            this.setState({
                prevoznici: result.data,
            });
        }
    }catch (error){
        alert("Nije uspelo dobavljanje.");
    }
}

goToEdit(linijaId){
    this.props.history.push('/linije/edit/' + linijaId);

}

async doAdd(){
    try{
        await BusAxios.post("/linije/", this.state.linija);
    

    let linija = {
        brojMesta:0,
        cenaKarte:0.00,
        destinacija: "",
        vremePolaska: "",
        prevoznikId: -1,
    };
    this.setState({ linja: linija});

    this.getLinije(0);
}catch(error){
    alert("Nije uspelo dodavanje.")
}

}
async doDelete(linijaId){
    try{
        await BusAxios.delete("/linije/" + linijaId);
        this.getLinije(0);
    }catch(error){
        alert("Nije uspelo brisanje zato što je linija rezervisana.")
    }
}

addValueInputChange(event){
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let linija = this.state.linija;
    linija[name] = value;

    this.setState({ linija:linija});
}

searchValueInputChange(event){
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search});
}

doSearch(){
    this.getLinije(0);
}

async rezervisi(id){
    try{
        await BusAxios.post(`/linije/${id}/rezervisi`);
        this.getLinije(0);
    }catch(error){
        alert("Nije moguce promeniti stanje.");
    }
}

hiddenForm(){
    const currentState = this.state.active;
    this.setState({ active: !currentState});
}



render(){
    return(
        <div>
            <h1>Linije</h1>
            <Form>
            <Form.Group>
                    <Form.Label>Broj mesta</Form.Label>
                    <Form.Control
                    onChange={(event) => this.addValueInputChange(event)}
                    name="brojMesta"
                    value={this.state.linija.brojMesta}
                    as="input"
                ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Cena karte</Form.Label>
                    <Form.Control
                      onChange={(event) => this.addValueInputChange(event)}
                      name="cenaKarte"
                      value={this.state.linija.cenaKarte}
                      as="input"
                ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Destinacija</Form.Label>
                    <Form.Control
                    onChange={(event) => this.addValueInputChange(event)}
                    name="destinacija"
                    value={this.state.linija.destinacija}
                    as="input">
                    </Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Vreme polaska</Form.Label>
                    <Form.Control
                      onChange={(event) => this.addValueInputChange(event)}
                      name="vremePolaska"
                      value={this.state.linija.vremePolaska}
                      as="input"
                ></Form.Control>
                </Form.Group>

                <Form.Group>
                    <Form.Label>Naziv prevoznika</Form.Label>
                    <Form.Control
                           onChange={(event) => this.addValueInputChange(event)}
                           name="prevoznikId"
                           value={this.state.linija.prevoznikId}
                           as="select"
                         >
                           <option value={-1}></option>
                           {this.state.prevoznici.map((prevoznik) => {
                             return (
                               <option value={prevoznik.id} key={prevoznik.id}>
                                 {prevoznik.naziv}
                               </option>
                             );
                           })}
                    </Form.Control>
                </Form.Group>
                <Button variant ="primary" onClick={() => this.doAdd()}>
                    Add
                </Button>
            </Form>
            <br/>
            <Form.Check onClick={() => this.hiddenForm()} type ="checkbox" label = "Prikazi formu za pretragu"/>
                
            <Form style={{marginTop:35}} className= {this.state.active ? 'hidden' : null}>
                <Form.Group>
                    <Form.Label>Destinacija</Form.Label>
                    <Form.Control
                    value={this.state.search.destinacija}
                    name="destinacija"
                    as="input"
                    onChange={(e) => this.searchValueInputChange(e)}
                ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Prevoznik</Form.Label>
                    <Form.Control
                    onChange={(event) => this.searchValueInputChange(event)}
                    name="prevoznikId"
                    value={this.state.search.prevoznikId}
                    as="select"
                  >
                    <option value={-1}></option>
                    {this.state.prevoznici.map((prevoznik) => {
                      return (
                        <option value={prevoznik.id} key={prevoznik.id}>
                          {prevoznik.naziv}
                        </option>
                      );
                    })}
                </Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Maksimalna cena</Form.Label>
                    <Form.Control
                    value={this.state.search.maxCenaKarte}
                    name="maxCenaKarte"
                    as="input"
                    onChange={(e) => this.searchValueInputChange(e)}
                    ></Form.Control>
                </Form.Group>
                <Button onClick={() => this.doSearch()}>Traži</Button>
            </Form>

            <ButtonGroup style={{ marginTop: 25 }}>
                <Button
                disabled={this.state.pageNo==0} onClick={()=>this.getLinije(this.state.pageNo-1)}>
                    Prethodna
                </Button>
                <Button
                disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getLinije(this.state.pageNo+1)}>
                Sledeća
                </Button>
            </ButtonGroup>

            <Table bordered striped style={{ marginTop: 5 }}>
                <thead className="thead-dark">
                    <tr>
                        <th>Naziv prevoznika</th>
                        <th>Destinacija</th>
                        <th>Broj Mesta</th>
                        <th>Vreme polaska</th>
                        <th>Cena Karte</th>
                        <th colSpan={2}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {this.state.linije.map((linija) => {
                       return(
                           <tr key ={linija.id}>
                               <td>{linija.prevoznik.naziv}</td>
                               <td>{linija.destinacija}</td>
                               <td>{linija.brojMesta}</td>
                               <td>{linija.vremePolaska}</td>
                               <td>{linija.cenaKarte}</td>
                               <td>
                                   <Button
                                   disabled={linija.rezervacijaId === 3}
                                   variant="info"
                                   onClick={() => this.rezervisi(linija.id)}
                                   >
                                       Rezerviši
                                   </Button>

                                   <Button
                                   variant="warning"
                                   onClick={() => this.goToEdit(linija.id)}
                                   style={{ marginLeft: 5 }}
                                   >
                                       Izmeni
                                   </Button>

                                   <Button
                                   variant="danger"
                                   onClick={() => this.doDelete(linija.id)}
                                   style={{ marginLeft: 5 }}
                                   >
                                       Obriši
                                   </Button>
                               </td>
                           </tr>
                       );
                    })}
                </tbody>   
            </Table>
        </div>
        
        
    );
}
}

export default Linija;

import React, { Component } from 'react';
import {
    Grid,
    List,
    ListItem,
    ListItemText,
    ListItemSecondaryAction,
    IconButton,Switch,
    Typography, Modal
} from '@material-ui/core';
import InsideModalAssumir from "./InsideModalAssumir";
import InsideModalDetalhar from "./InsideModalDetalhar";
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import Search from '@material-ui/icons/Search'
import DoneOutline from '@material-ui/icons/DoneOutline';
import InsideModalFinalizar from './InsideModalFinalizar';
import Header from './header';

export default class home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            open: false,
            openFinalizar: false,
            openListar: false,
            os: [],
            osAssumidos: [],
            instaladores: [],
            currId: "",
            currOS: [],
            tipo: "naoAssumidos",
            instalador: {
                id: 2,
            }
        }
        this.triggerUpdateNaoAssumidas.bind(this);
        this.changeSelectedInstaller.bind(this);
    }

    //Recupera lista e adiciona os OS sem instaladores para uma lista  
    triggerUpdateNaoAssumidas = async () => {
        const osCall = await fetch('http://localhost:8082/api/os/notHasInstaller', {
            headers: {
                'Access-Control-Allow-Origin': '*'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        });
        const responseList = await osCall.json();
        this.setState({ os: [] });
        for (var i = 0; i < responseList.length; i++) {
            const auxOS = {
                id: responseList[i].id,
                plano: responseList[i].plano,
                endereco: responseList[i].endereco,
                cliente: responseList[i].cliente,
                instalador: responseList[i].instalador,
                horario: responseList[i].horario,
                dataContratacao: responseList[i].dataContratacao,
                dataInicioInstalacao: responseList[i].dataInicioInstalacao,
                dataFinalInstalacao: responseList[i].dataFinalInstalacao,
            };
            this.setState({
                os: [...this.state.os, auxOS]
            });

        }
    };

    triggerUpdateInstallers = async () => {
        const installCall = await fetch('http://localhost:8082/api/os/getAllInstallers', {
            headers: {
                'Access-Control-Allow-Origin': '*'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        });
        const responseList = await installCall.json();
        this.setState({ instaladores: [] });
        for (var i = 0; i < responseList.length; i++) {
            const auxInst = {
                id: responseList[i].id,
                username: responseList[i].username,
                password: responseList[i].password,
                nome: responseList[i].nome,
                email: responseList[i].email,
            };
            this.setState({
                instaladores: [...this.state.instaladores, auxInst]
            })
        }
    };

    triggerUpdateAssumidas = async () => {
        const osCall = await fetch(`http://localhost:8082/api/os/${this.state.instalador.id}`, {
            headers: {
                'Access-Control-Allow-Origin': '*'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        });
        const responseList = await osCall.json();
        this.setState({ osAssumidos: [] });
        for (var i = 0; i < responseList.length; i++) {
            const auxOS = {
                id: responseList[i].id,
                plano: responseList[i].plano,
                endereco: responseList[i].endereco,
                cliente: responseList[i].cliente,
                instalador: responseList[i].instalador,
                horario: responseList[i].horario,
                dataContratacao: responseList[i].dataContratacao,
                dataInicioIntalacao: responseList[i].dataInicioIntalacao,
                dataFinalInstalacao: responseList[i].dataFinalInstalacao,
            };
            this.setState({
                osAssumidos: [...this.state.osAssumidos, auxOS]
            });

        }
    };

    //Quando a tela carrega, chama triggerUpdate
    async componentDidMount() {
        this.triggerUpdateAssumidas();
        this.triggerUpdateNaoAssumidas();
        this.triggerUpdateInstallers();
    };

    printList() {
        return this.state.os.map(os =>
            <ListItem key={os.id}>
                <ListItemText
                    primary={os.plano}
                    secondary={"Cliente: " + os.cliente.nome} />
                <ListItemSecondaryAction >
                    <IconButton aria-label="Add" onClick={() => this.handleOpen("naoAssumidos", os)}>
                        <AddCircleOutlineIcon/>
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }

    printAssumidos() {
        return this.state.osAssumidos.map(osAssumido =>
            <ListItem key={osAssumido.id}>
                <ListItemText
                    primary={osAssumido.plano}
                    secondary={osAssumido.dataFinalInstalacao ? ("Finalizada em " + osAssumido.dataFinalInstalacao.split('T')[0]) : ("Cliente: " + osAssumido.cliente.nome) } />
                <ListItemSecondaryAction >
                    {
                        !osAssumido.dataFinalInstalacao ?  <IconButton aria-label="Finish" onClick={() => this.handleOpen("assumidos", osAssumido)}>
                                                                <DoneOutline />
                                                            </IconButton> : 
                                                            <IconButton aria-label="Detail" onClick={() => this.handleOpen("assumidosList", osAssumido)}>
                                                                <Search/>
                                                            </IconButton>
                    }
                    
                </ListItemSecondaryAction>
            </ListItem>
        );
    }

    handleOpen = (m, os) => {
        switch(m){
            case 'assumidos':
                this.setState({openFinalizar: true, currId: os.id, currOS: os });
                break;
            case 'naoAssumidos':
                this.setState({open: true, currId: os.id, currOS: os });	
                break;
            case 'assumidosList':
                this.setState({openListar: true, currId: os.id, currOS: os });
                break;
        }
    };

    handleClose = (m) => {
        switch(m){
            case 'assumidos':
                this.setState({ openFinalizar: false });
                break;
            case 'naoAssumidos':
                this.setState({ open: false });
                break;
            case 'assumidosList':
                this.setState({ openListar: false });
                break;
        }
    };

    changeSelectedInstaller= async (installer) =>{
        console.log('onChangeInstall');
        this.setState({instalador: {id: installer}});
        if(this.state.instalador){
            this.triggerUpdateAssumidas();
            this.triggerUpdateNaoAssumidas();
        }
    }

    render() {
        return (
            <div>
                <Header onChangeS={this.changeSelectedInstaller} lista={this.state.instaladores}/>
                <Modal open={this.state.open} onClose={()=>this.handleClose('naoAssumidos')}>
                    <InsideModalAssumir 
                        onClose={()=>this.handleClose('naoAssumidos')} 
                        triggerUpdate={this.triggerUpdateNaoAssumidas}
                        currId={this.state.currId} 
                        ordemS={this.state.currOS}
                        instalador={this.state.instalador}/>
                </Modal>
                <Modal open={this.state.openFinalizar} onClose={()=>this.handleClose('assumidos')}>
                    <InsideModalFinalizar 
                        onClose={()=>this.handleClose('assumidos')} 
                        triggerUpdate={this.triggerUpdateAssumidas}
                        currId={this.state.currId} 
                        ordemS={this.state.currOS}
                        instalador={this.state.instalador}/>
                </Modal>
                <Modal open={this.state.openListar} onClose={()=>this.handleClose('assumidosList')}>
                    <InsideModalDetalhar 
                        onClose={()=>this.handleClose('assumidosList')} 
                        currId={this.state.currId} 
                        ordemS={this.state.currOS}/>
                </Modal>
                <Grid container spacing={10} style={{ margin: 24, alignContent: 'center' }}>
                    <Grid item xs={8}>
                        <Typography variant="h6">
                            {this.state.tipo === 'naoAssumidos'? 'Lista de Ordens de Serviço sem Instalador:': 'Suas Ordens de Serviço:'}
                         </Typography>
                    </Grid>
                    <Grid container item xs={4} justify="center" alignItems="center">
                            <Typography>
                                Listar Ordem de Serviço Assumidas
                            </Typography>
                            <Switch value="checkedA" inputProps={{ 'aria-label': 'Switch A'}} color='primary' onChange={(evt)=>{
                                this.triggerUpdateAssumidas(); this.triggerUpdateNaoAssumidas();
                                evt.target.checked? this.setState({tipo: "assumidos"}) : this.setState({tipo: "naoAssumidos"});
                                }}/>
                    </Grid>
                    <Grid item xs={4}></Grid>
                    <Grid item xs={4} style={{ maxHeight: 500, overflow: 'auto', alignContent: 'center' }}>
                        <List dense={false} >
                            {this.state.tipo === 'naoAssumidos'? this.printList(): this.printAssumidos()}
                        </List>
                    </Grid>
                </Grid>
            </div>
        )
    }
}

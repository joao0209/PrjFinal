import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid';
import { Button, Typography, TextField } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';



function getModalStyle() {
    const top = 50;
    const left = 50;

    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
    };
}

const styles = theme => ({
    root: {
        flexGrow: 1,
        maxWidth: 752,
    },
    demo: {
        backgroundColor: theme.palette.background.paper,
    },
    title: {
        margin: `${theme.spacing.unit * 4}px 0 ${theme.spacing.unit * 2}px`,
    },
    paper: {
        position: 'absolute',
        width: theme.spacing.unit * 50,
        backgroundColor: theme.palette.background.paper,
        boxShadow: theme.shadows[5],
        padding: theme.spacing.unit * 4,
        outline: 'none',
    },
});

export default withStyles(styles)(class InsideModalFinalizar extends Component {
    state = {
        dataInicioIntalacao: "2019-12-01",
        dataFinalInstalacao: "2019-12-01",
    }

    onClickHandler = async (dataInicioIntalacao, dataFinalInstalacao) => {
        console.log('on click handler');
        await fetch(`http://localhost:8082/api/os/finalizar?dataInicioIntalacao=${dataInicioIntalacao}&dataFinalInstalacao=${dataFinalInstalacao}`, {
            method: "PUT", headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            }, body: JSON.stringify(this.props.ordemS)
        });
        this.props.triggerUpdate();
        this.props.onClose();
    }

    onChangeHandler = (evt) => {
        switch (evt.target.id) {
            case 'tfInicio':
                console.log('inicii');
                console.log(evt.target.value);
                this.setState({ dataInicioIntalacao: evt.target.value });
                return
            case 'tfFinal':
                console.log('finale');
                console.log(evt.target.value);
                this.setState({ dataFinalInstalacao: evt.target.value });
                return
            default:
                return 'nothing'
        }
    }

    render() {
        const { classes } = this.props;
        return (
            <Grid container className={classes.paper} style={getModalStyle()} direction="column" spacing={2} alignItems="center">
                <Grid item>
                    <Typography>Cliente: {this.props.ordemS.cliente.nome}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Tipo de plano: {this.props.ordemS.plano}</Typography>
                </Grid>
                <Grid item>
                    <Typography>Data de contratação: {this.props.ordemS.dataContratacao}</Typography>
                </Grid>
                <Grid item>
                    <TextField id="tfInicio" label="Inicio: " onChange={this.onChangeHandler}
                        type="date"
                        defaultValue="2019-12-01"
                        InputLabelProps={{
                            shrink: true,
                        }} />
                </Grid>
                <Grid item>
                    <TextField id="tfFinal" label="Termino:" onChange={this.onChangeHandler}
                        type="date"
                        defaultValue="2019-12-01"
                        InputLabelProps={{
                            shrink: true,
                        }} />
                </Grid>
                <Grid item xs={12}>
                    <Button variant="contained" id="btnAdicionar" onClick={() => this.onClickHandler(this.state.dataInicioIntalacao, this.state.dataFinalInstalacao)}>
                        <Typography>Finalizar Ordem de Serviço</Typography>
                    </Button>
                </Grid>
            </Grid>
        )
    }
})

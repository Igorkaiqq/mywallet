import { RouterModule, Routes } from "@angular/router";
import { CadastroUsuarioComponent } from "./cadastro-usuario/cadastro-usuario.component";
import { LoginComponent } from "./login/login.component";
import { WalletAppComponent } from "./wallet-app/wallet-app.component";
import { CadReceitaComponent } from "./cad-receita/cad-receita.component";
// import { HttpClienteModule } from '@angular/common/http';

export const routes: Routes = [
    {
        path: 'cadastrar-usuario',
        component: CadastroUsuarioComponent
    },
    {
        path: 'wallet',
        component: WalletAppComponent
    },
    {
        path: 'cad-receita',
        component: CadReceitaComponent
    },
    {
        path: '**',
        component: LoginComponent
    }
];

export const AppRoutingModule = RouterModule.forRoot(routes);

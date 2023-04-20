package com.example.cotacaofacil.di

import com.example.cotacaofacil.data.model.SpinnerListHelper
import com.example.cotacaofacil.data.repository.bodyCompany.BodyCompanyRepositoryImpl
import com.example.cotacaofacil.data.repository.bodyCompany.contract.BodyCompanyRepository
import com.example.cotacaofacil.data.repository.history.HistoryRepositoryImpl
import com.example.cotacaofacil.data.repository.history.contract.HistoryRepository
import com.example.cotacaofacil.data.repository.partner.PartnerRepositoryImpl
import com.example.cotacaofacil.data.repository.partner.contract.PartnerRepository
import com.example.cotacaofacil.data.repository.product.ProductRepositoryImpl
import com.example.cotacaofacil.data.repository.product.contract.ProductRepository
import com.example.cotacaofacil.data.repository.user.UserRepositoryImpl
import com.example.cotacaofacil.data.repository.user.contract.UserRepository
import com.example.cotacaofacil.data.service.cnpj.BodyCompanyServiceImpl
import com.example.cotacaofacil.data.service.cnpj.CnpjServiceImpl
import com.example.cotacaofacil.data.service.cnpj.contract.BodyCompanyService
import com.example.cotacaofacil.data.service.history.HistoryServiceImpl
import com.example.cotacaofacil.data.service.history.contract.HistoryService
import com.example.cotacaofacil.data.service.partner.PartnerServiceImpl
import com.example.cotacaofacil.data.service.partner.contract.PartnerService
import com.example.cotacaofacil.data.service.product.ProductServiceImpl
import com.example.cotacaofacil.data.service.product.contract.ProductService
import com.example.cotacaofacil.data.service.settings.retrofitConfig
import com.example.cotacaofacil.data.service.user.UserFirebaseService
import com.example.cotacaofacil.data.service.user.contract.UserService
import com.example.cotacaofacil.domain.usecase.history.GetAllItemHistoryUseCaseImpl
import com.example.cotacaofacil.domain.usecase.history.contract.GetAllItemHistoryUseCase
import com.example.cotacaofacil.domain.usecase.login.LoginUseCaseImpl
import com.example.cotacaofacil.domain.usecase.login.contract.LoginUseCase
import com.example.cotacaofacil.domain.usecase.partner.*
import com.example.cotacaofacil.domain.usecase.partner.contract.*
import com.example.cotacaofacil.domain.usecase.product.GetAllListSpinnerOptionsUseCaseImpl
import com.example.cotacaofacil.domain.usecase.product.SaveProductionUseCaseImpl
import com.example.cotacaofacil.domain.usecase.product.contract.GetAllListSpinnerOptionsUseCase
import com.example.cotacaofacil.domain.usecase.product.contract.SaveProductionUseCase
import com.example.cotacaofacil.domain.usecase.register.RegisterUseCaseImpl
import com.example.cotacaofacil.domain.usecase.register.contract.RegisterUseCase
import com.example.cotacaofacil.presentation.viewmodel.history.HistoryViewModel
import com.example.cotacaofacil.presentation.viewmodel.partner.PartnerViewModel
import com.example.cotacaofacil.presentation.viewmodel.login.LoginViewModel
import com.example.cotacaofacil.presentation.viewmodel.product.AddProductViewModel
import com.example.cotacaofacil.presentation.viewmodel.register.RegisterViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {

    //viewModel
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { HistoryViewModel(get(), get(), get()) }
    viewModel { params -> (PartnerViewModel(get(), get(), get(), get(), get(),get(), user = params.get())) }
    viewModel { params -> (AddProductViewModel(get(), get(), get())) }


    //useCase
    factory<RegisterUseCase> { RegisterUseCaseImpl(get(), get(), get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(get(), get()) }
    factory<ValidationCnpjUseCase> { ValidationCnpjUseCaseImpl(get()) }
    factory<GetAllPartnerModelUseCase> { GetAllPartnerModelUseCaseImpl(get())}
    factory<AddRequestPartnerUseCase> { AddRequestRequestPartnerUseCaseImpl(get(), get(), get())}
    factory<RejectRequestPartnerUseCase> { RejectRequestPartnerUseCaseImpl(get(), get(), get())}
    factory<AcceptRequestPartnerUseCase> { AcceptRequestPartnerUseCaseImpl(get(), get(), get())}
    factory<GetAllItemHistoryUseCase> { GetAllItemHistoryUseCaseImpl(get()) }
    factory<GetAllListSpinnerOptionsUseCase> { GetAllListSpinnerOptionsUseCaseImpl(get()) }
    factory<SaveProductionUseCase> { SaveProductionUseCaseImpl(get()) }



    //repository
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<BodyCompanyRepository> { BodyCompanyRepositoryImpl(get(), get()) }
    factory<PartnerRepository> { PartnerRepositoryImpl(get()) }
    factory<HistoryRepository> { HistoryRepositoryImpl(get()) }
    factory<ProductRepository> { ProductRepositoryImpl(get(), get(), get()) }


    //service
    single<UserService> { UserFirebaseService(get(), get()) }
    single<PartnerService> { PartnerServiceImpl(get()) }
    single<BodyCompanyService> { BodyCompanyServiceImpl(get()) }
    single<HistoryService> { HistoryServiceImpl(get())}
    single<ProductService> { ProductServiceImpl(get())}

    //helper
    single<SpinnerListHelper> { SpinnerListHelper() }

    single { retrofitConfig }
    single { get<Retrofit>().create(CnpjServiceImpl::class.java) }
    single { Firebase.firestore }
    single { Firebase.auth }
}
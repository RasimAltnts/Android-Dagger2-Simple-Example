package com.example.dagger2

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Component(modules = [(Drive::class)])
interface Vehicle {
    fun inject(activity: MainActivity)
}


interface IvehicleStatus{
    fun gas(isStatus:String)
    fun brake(isStatus: String)
}

@Module
class Drive @Inject constructor() {

    private var mIvehicleStatus: IvehicleStatus?= null

    @Provides
    fun gas():Boolean {
        mIvehicleStatus?.let {
            mIvehicleStatus?.gas("Active")
            mIvehicleStatus?.brake("Deactivate")
            return true
        }
        mIvehicleStatus?.gas("Deactive")
        mIvehicleStatus?.brake("Active")
        return false
    }

    @Provides
    fun brake():Boolean {
        mIvehicleStatus?.let {
            mIvehicleStatus?.gas("Deactive")
            mIvehicleStatus?.brake("Active")
            return true
        }

        mIvehicleStatus?.gas("Active")
        mIvehicleStatus?.brake("Deactivate")
        return false
    }

    @Provides
    fun setListener(IvehicleStatus: IvehicleStatus):Boolean {
        this.mIvehicleStatus = IvehicleStatus
        return true
    }
}
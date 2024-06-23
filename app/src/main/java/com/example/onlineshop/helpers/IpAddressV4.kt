package com.example.onlineshop.helpers

import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*


class IpAddressV4 {


    fun getLocalIPv4Address(): String? {
        try {
            val interfaces = NetworkInterface.getNetworkInterfaces().toList()
            for (networkInterface in interfaces) {
                val addresses = networkInterface.inetAddresses.toList()
                for (address in addresses) {
                    if (!address.isLoopbackAddress && address is InetAddress && address.hostAddress.indexOf(':') == -1) {
                        return address.hostAddress
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}
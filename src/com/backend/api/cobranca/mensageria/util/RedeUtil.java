package com.backend.api.cobranca.mensageria.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RedeUtil {

    public static void verificarEConfigurarRede() {

        try {

            boolean temGateway = false;
            boolean temDNS = false;

            Process process = Runtime.getRuntime().exec("ipconfig /all");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "CP850"));

            String linha;

            while ((linha = reader.readLine()) != null) {

                if (linha.toLowerCase().contains("gateway padrão")
                        || linha.toLowerCase().contains("default gateway")) {

                    if (!linha.trim().endsWith(":")) {
                        temGateway = true;
                    }
                }

                if (linha.toLowerCase().contains("servidores dns")
                        || linha.toLowerCase().contains("dns servers")) {

                    temDNS = true;
                }
            }

            if (temGateway && temDNS) {
                System.out.println("Rede já possui Gateway e DNS configurados.");
                return;
            }

            System.out.println("Gateway ou DNS não encontrado. Configurando...");

            configurarRede();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void configurarRede() {

        try {

            String interfaceRede = "Ethernet";

            String gateway = "192.168.0.1";
            String dns1 = "8.8.8.8";
            String dns2 = "8.8.4.4";

            String cmdGateway = "netsh interface ip set address name=\""
                    + interfaceRede + "\" gateway=" + gateway;

            String cmdDns1 = "netsh interface ip set dns name=\""
                    + interfaceRede + "\" static " + dns1;

            String cmdDns2 = "netsh interface ip add dns name=\""
                    + interfaceRede + "\" " + dns2 + " index=2";

            Runtime.getRuntime().exec(cmdGateway).waitFor();
            Runtime.getRuntime().exec(cmdDns1).waitFor();
            Runtime.getRuntime().exec(cmdDns2).waitFor();

            System.out.println("Gateway e DNS configurados.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

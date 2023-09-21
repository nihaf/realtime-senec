package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatteryManagementSystemDto {
    @SerializedName("ALARM_STATUS")
    private String[] alarm_status;
    @SerializedName("BATTERY_STATUS")
    private String[] battery_status;
    @SerializedName("BL")
    private String[] bl;
    @SerializedName("BMS_READY_FLAG")
    private String bms_ready_flag;
    @SerializedName("BMS_STATUS")
    private String bms_status;
    @SerializedName("BMS_STATUS_TIMESTAMP")
    private String bms_status_timestamp;
    @SerializedName("CELL_BALANCE_STATUS")
    private String cell_balance_status;
    @SerializedName("CELL_TEMPERATURES_MODULE_A")
    private String[] cell_temperatures_module_a;
    @SerializedName("CELL_TEMPERATURES_MODULE_B")
    private String[] cell_temperatures_module_b;
    @SerializedName("CELL_TEMPERATURES_MODULE_C")
    private String[] cell_temperatures_module_c;
    @SerializedName("CELL_TEMPERATURES_MODULE_D")
    private String[] cell_temperatures_module_d;
    @SerializedName("CELL_VOLTAGES_MODULE_A")
    private String[] cell_voltages_module_a;
    @SerializedName("CELL_VOLTAGES_MODULE_B")
    private String[] cell_voltages_module_b;
    @SerializedName("CELL_VOLTAGES_MODULE_C")
    private String[] cell_voltages_module_c;
    @SerializedName("CELL_VOLTAGES_MODULE_D")
    private String[] cell_voltages_module_d;
    @SerializedName("CHARGED_ENERGY")
    private String[] charged_energy;
    @SerializedName("CHARGE_CURRENT_LIMIT")
    private String[] charge_current_limit;
    @SerializedName("COMMERRCOUNT")
    private String commerrcount;
    @SerializedName("CURRENT")
    private String[] current;
    @SerializedName("CYCLES")
    private String[] cycles;
    @SerializedName("DERATING")
    private String derating;
    @SerializedName("DISCHARGED_ENERGY")
    private String[] discharged_energy;
    @SerializedName("DISCHARGE_CURRENT_LIMIT")
    private String[] discharge_current_limit;
    @SerializedName("ERROR")
    private String error;
    @SerializedName("FAULTLINECOUNT")
    private String faultlinecount;
    @SerializedName("FW")
    private String[] fw;
    @SerializedName("HW_EXTENSION")
    private String[] hw_extension;
    @SerializedName("HW_MAINBOARD")
    private String[] hw_mainboard;
    @SerializedName("MANUFACTURER")
    private String manufacturer;
    @SerializedName("MAX_CELL_VOLTAGE")
    private String[] max_cell_voltage;
    @SerializedName("MAX_TEMP")
    private String max_temp;
    @SerializedName("MIN_CELL_VOLTAGE")
    private String[] min_cell_voltage;
    @SerializedName("MIN_TEMP")
    private String min_temp;
    @SerializedName("MODULES_CONFIGURED")
    private String modules_configured;
    @SerializedName("MODULE_COUNT")
    private String module_count;
    @SerializedName("NOM_CHARGEPOWER_MODULE")
    private String nom_chargepower_module;
    @SerializedName("NOM_DISCHARGEPOWER_MODULE")
    private String nom_dischargepower_module;
    @SerializedName("NR_INSTALLED")
    private String nr_installed;
    @SerializedName("PROTOCOL")
    private String protocol;
    @SerializedName("RECOVERLOCKED")
    private String recoverlocked;
    @SerializedName("SERIAL")
    private String[] serial;
    @SerializedName("SN")
    private String[] sn;
    @SerializedName("SOC")
    private String[] soc;
    @SerializedName("SOH")
    private String[] soh;
    @SerializedName("STATUS")
    private String[] status;
    @SerializedName("SYSTEM_SOC")
    private String system_soc;
    @SerializedName("TEMP_MAX")
    private String[] temp_max;
    @SerializedName("TEMP_MIN")
    private String[] temp_min;
    @SerializedName("TF_ERROR")
    private String tf_error;
    @SerializedName("VOLTAGE")
    private String voltage;
    @SerializedName("WIZARD_ABORT")
    private String wizard_abort;
    @SerializedName("WIZARD_CONFIRM")
    private String wizard_confirm;
    @SerializedName("WIZARD_DCCONNECT")
    private String wizard_dcconnect;
    @SerializedName("WIZARD_START")
    private String wizard_start;
    @SerializedName("WIZARD_STATE")
    private String wizard_state;
}
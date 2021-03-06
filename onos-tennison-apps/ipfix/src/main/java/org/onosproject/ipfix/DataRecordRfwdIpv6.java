/*
 * Copyright 2015 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.ipfix;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.onlab.packet.Ip6Address;
import org.onlab.packet.IpAddress;
import org.onlab.packet.MacAddress;
import org.onosproject.ipfix.AbstractIpRecordInterface;
import org.onosproject.ipfix.Data;
import org.onosproject.ipfix.packet.DataRecord;
import org.onosproject.ipfix.packet.HeaderException;
import org.onosproject.ipfix.packet.Ie;
import org.onosproject.ipfix.packet.InformationElement;
import org.onosproject.ipfix.packet.TemplateRecord;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;

/**
 * IPFIX Data record for Reactive Forwarding application with IPv4 fields matching.
 */
public class DataRecordRfwdIpv6 extends DataRecord implements AbstractIpRecordInterface {
    public static final int TEMPLATE_ID = 333;
    public static final int FIELD_COUNT = 19;
    public static final int LENGTH = 118;

    private IpAddress exporterIPv4Address;
    private IpAddress exporterIPv6Address;
    private long flowStartMilliseconds;
    private long flowEndMilliseconds;
    private long octetDeltaCount;
    private long packetDeltaCount;
    private int ingressInterface;
    private int egressInterface;
    private MacAddress sourceMacAddress;
    private MacAddress destinationMacAddress;
    private short ethernetType;
    private short vlanId;
    private Ip6Address sourceIPv6Address;
    private Ip6Address destinationIPv6Address;
    private int flowLabelIpv6;
    private byte protocolIdentifier;
    private byte ipClassOfService;
    private int sourceTransportPort;
    private int destinationTransportPort;

    //CHECKSTYLE:OFF
    /**
     * IPFIX Data record for Reactive Forwarding application with IPv6 fields matching.
     *
     * @param exporterIpv4 IPv4 address of the IPFIX exporter
     * @param exporterIpv6 IPv6 address of the IPFIX exporter, used for DPID
     * @param start start timestamp of the flow
     * @param end end timestamp of the flow
     * @param octets number of bytes matched by the flow
     * @param packets number of packets matched by the flow
     * @param intfIn switch input interface of the flow
     * @param intfOut switch output interface of the flow
     * @param srcMac source MAC address
     * @param dstMac destination MAC address
     * @param etherType etherType field of the flow
     * @param vlan VLAN ID of the flow
     * @param srcIp6 source IPv6 address of the flow
     * @param dstIp6 destination IPv6 address of the flow
     * @param flowLabel IPv6 flow label of the flow
     * @param proto IPv6 next-header of the flow
     * @param tos IPv6 Traffic Class field
     * @param srcPort source transport protocol port
     * @param dstPort destination transport protocol port
     */
    public DataRecordRfwdIpv6(IpAddress exporterIpv4,
            Ip6Address exporterIpv6,
            long start,
            long end,
            long octets,
            long packets,
            int intfIn,
            int intfOut,
            MacAddress srcMac,
            MacAddress dstMac,
            short etherType,
            short vlan,
            Ip6Address srcIp6,
            Ip6Address dstIp6,
            int flowLabel,
            byte proto,
            byte tos,
            int srcPort,
            int dstPort) {
        //CHECKSTYLE:ON

        exporterIPv4Address = exporterIpv4;
        exporterIPv6Address = exporterIpv6;
        flowStartMilliseconds = start;
        flowEndMilliseconds = end;
        octetDeltaCount = octets;
        packetDeltaCount = packets;
        ingressInterface = intfIn;
        egressInterface = intfOut;
        sourceMacAddress = srcMac;
        destinationMacAddress = dstMac;
        ethernetType = etherType;
        vlanId = vlan;
        sourceIPv6Address = srcIp6;
        destinationIPv6Address = dstIp6;
        flowLabelIpv6 = flowLabel;
        protocolIdentifier = proto;
        ipClassOfService = tos;
        sourceTransportPort = srcPort;
        destinationTransportPort = dstPort;
       }

    public int getTemplateId() {
        return TEMPLATE_ID;
    }
    
    public List<Data> getData() {
        List<Data> dataList = new ArrayList<Data>();
        dataList.add(new Data<IpAddress>("exporterIPv4Address", exporterIPv4Address));
        dataList.add(new Data<IpAddress>("exporterIPv6Address", exporterIPv6Address));
        dataList.add(new Data<Long>("flowStartMilliseconds", flowStartMilliseconds));
        dataList.add(new Data<Long>("flowEndMilliseconds", flowEndMilliseconds));
        dataList.add(new Data<Long>("octetDeltaCount", octetDeltaCount));
        dataList.add(new Data<Long>("packetDeltaCount", packetDeltaCount));
        dataList.add(new Data<Integer>("ingressInterface", ingressInterface));
        dataList.add(new Data<Integer>("egressInterface", egressInterface));
        dataList.add(new Data<MacAddress>("sourceMacAddress", sourceMacAddress));
        dataList.add(new Data<MacAddress>("destinationMacAddress", destinationMacAddress));
        dataList.add(new Data<Short>("ethernetType", ethernetType));
        dataList.add(new Data<Short>("vlanId", vlanId));
        dataList.add(new Data<Ip6Address>("sourceIPv6Address", sourceIPv6Address));
        dataList.add(new Data<Ip6Address>("destinationIPv6Address", destinationIPv6Address));
        dataList.add(new Data<Integer>("flowLabelIpv6", flowLabelIpv6));
        dataList.add(new Data<Byte>("protocolIdentifier", protocolIdentifier));
        dataList.add(new Data<Byte>("ipClassOfService", ipClassOfService));
        dataList.add(new Data<Integer>("sourceTransportPort", sourceTransportPort));
        dataList.add(new Data<Integer>("destinationTransportPort", destinationTransportPort));
		return dataList;
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public byte[] getBytes() throws HeaderException {
        try {
            byte[] data = new byte[LENGTH];

            System.arraycopy(exporterIPv4Address.toOctets(), 0, data, 0, 4);
            System.arraycopy(exporterIPv6Address.toOctets(), 0, data, 4, 16);
            System.arraycopy(Longs.toByteArray(flowStartMilliseconds), 0, data, 20, 8);
            System.arraycopy(Longs.toByteArray(flowEndMilliseconds), 0, data, 28, 8);
            System.arraycopy(Longs.toByteArray(octetDeltaCount), 0, data, 36, 8);
            System.arraycopy(Longs.toByteArray(packetDeltaCount), 0, data, 44, 8);
            System.arraycopy(Ints.toByteArray(ingressInterface), 0, data, 52, 4);
            System.arraycopy(Ints.toByteArray(egressInterface), 0, data, 56, 4);
            System.arraycopy(sourceMacAddress.toBytes(), 0, data, 60, 6);
            System.arraycopy(destinationMacAddress.toBytes(), 0, data, 66, 6);
            System.arraycopy(Shorts.toByteArray(ethernetType), 0, data, 72, 2);
            System.arraycopy(Shorts.toByteArray(vlanId), 0, data, 74, 2);
            System.arraycopy(sourceIPv6Address.toOctets(), 0, data, 76, 16);
            System.arraycopy(destinationIPv6Address.toOctets(), 0, data, 92, 16);
            System.arraycopy(Ints.toByteArray(flowLabelIpv6), 0, data, 108, 4);
            data[112] = protocolIdentifier;
            data[113] = ipClassOfService;
            System.arraycopy(Arrays.copyOfRange(Ints.toByteArray(sourceTransportPort),2,4), 0, data, 114, 2);
            System.arraycopy(Arrays.copyOfRange(Ints.toByteArray(destinationTransportPort),2,4), 0, data, 116, 2);


            return data;
        } catch (Exception e) {
            throw new HeaderException("Error while generating the bytes: " + e.getMessage());
        }
    }

    /**
     * IPFIX Template record for Reactive Forwarding application with IPv4 fields matching.
     *
     * @return TemplateRecord IPFIX Template Record
     */
    public static TemplateRecord getTemplateRecord() {

        TemplateRecord tr = new TemplateRecord();
        tr.setTemplateID(TEMPLATE_ID);
        tr.setFieldCount(FIELD_COUNT);

        List<InformationElement> ieTemp = tr.getInformationElements();

        ieTemp.add(new InformationElement(Ie.exporterIPv4Address));
        ieTemp.add(new InformationElement(Ie.exporterIPv6Address));
        ieTemp.add(new InformationElement(Ie.flowStartMilliseconds));
        ieTemp.add(new InformationElement(Ie.flowEndMilliseconds));
        ieTemp.add(new InformationElement(Ie.octetDeltaCount));
        ieTemp.add(new InformationElement(Ie.packetDeltaCount));
        ieTemp.add(new InformationElement(Ie.ingressInterface));
        ieTemp.add(new InformationElement(Ie.egressInterface));
        ieTemp.add(new InformationElement(Ie.sourceMacAddress));
        ieTemp.add(new InformationElement(Ie.destinationMacAddress));
        ieTemp.add(new InformationElement(Ie.ethernetType));
        ieTemp.add(new InformationElement(Ie.vlanId));
        ieTemp.add(new InformationElement(Ie.sourceIPv6Address));
        ieTemp.add(new InformationElement(Ie.destinationIPv6Address));
        ieTemp.add(new InformationElement(Ie.flowLabelIPv6));
        ieTemp.add(new InformationElement(Ie.protocolIdentifier));
        ieTemp.add(new InformationElement(Ie.ipClassOfService));
        ieTemp.add(new InformationElement(Ie.sourceTransportPort));
        ieTemp.add(new InformationElement(Ie.destinationTransportPort));

        return tr;
    }
}

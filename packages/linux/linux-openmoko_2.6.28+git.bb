require linux.inc
require linux-openmoko.inc

DESCRIPTION_${PN} = "Linux ${KERNEL_RELEASE} kernel for the Openmoko Neo GSM Smartphones"

KERNEL_RELEASE = "2.6.28"
KERNEL_VERSION = "${KERNEL_RELEASE}"

FILESPATH = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/linux-openmoko-${KERNEL_RELEASE}"

OEV = "oe1"
PV = "${KERNEL_RELEASE}-${OEV}+gitr${SRCREV}"
PR = "r0"

SRC_URI = "\
  git://git.openmoko.org/git/kernel.git;protocol=git;branch=andy-tracking \
  file://openwrt-ledtrig-netdev.patch;patch=1 \
  file://defconfig-oe.patch \
"
S = "${WORKDIR}/git"

do_configure_prepend() {
	install -m 644 ./arch/arm/configs/gta02-moredrivers-defconfig ${WORKDIR}/defconfig-oe
	cat ${WORKDIR}/defconfig-oe.patch | patch -p0 -d ${WORKDIR}
}
